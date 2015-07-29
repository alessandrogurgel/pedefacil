package org.michenux.yourappidea.restaurante.pedido;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rey.material.app.SimpleDialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.widget.Button;

import org.michenux.yourappidea.BuildConfig;
import org.michenux.yourappidea.R;
import org.michenux.yourappidea.YourApplication;

/**
 * Created by alessandro.gurgel on 7/21/15.
 */
public class PedidoFragment extends Fragment
{
    private PedidoAdapter pedidoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            Log.i(YourApplication.LOG_TAG, "CardapioFragment.onCreate");
        }
        pedidoAdapter = new PedidoAdapter(this.getActivity(), PedidoAbertoManager.getInstance().getItems());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pedido_main_list, container, false);

        ListView listView = (ListView) view.findViewById(R.id.pedido_items_list);
        listView.setAdapter(pedidoAdapter);

        TextView total = (TextView)view.findViewById(R.id.text_pedido_total);
        final String valorTotal = PedidoAbertoManager.getInstance().getTotal();
        total.setText(valorTotal);

        final FragmentActivity activity = getActivity();

        final com.rey.material.widget.Button confirmarButton = (com.rey.material.widget.Button)view.findViewById(R.id.button_confirmar_pedido);

        handleConfirmButtonText(confirmarButton);

        if (PedidoAbertoManager.getInstance().getItems().isEmpty())
        {
            confirmarButton.setEnabled(false);
            confirmarButton.setBackgroundColor(getResources().getColor(R.color.disable_color));
            confirmarButton.setTextColor(getResources().getColor(R.color.secondary_text));
        }
        else{

            confirmarButton.setEnabled(true);
            handleConfirmButtonClickEvent(confirmarButton, valorTotal, total);

        }

        return view;

    }

    private void handleConfirmButtonText(Button confirmarButton) {
        if (PedidoAbertoManager.getInstance().hasConta())
        {
            confirmarButton.setText("Pagar a Conta");
            confirmarButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        }
        else{
            confirmarButton.setText("Confirmar");
            confirmarButton.setBackground(getResources().getDrawable(R.drawable.bg_bt_raise_color));
        }
    }

    private void handleConfirmButtonClickEvent(final Button confirmarButton, final String valorTotal, final TextView total)
    {
        if (!PedidoAbertoManager.getInstance().hasConta()){
            confirmarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SimpleDialog.Builder builder = new SimpleDialog.Builder(R.style.Material_App_Dialog_Simple_Light) {
                        public void onPositiveActionClicked(DialogFragment fragment)
                        {
                            PedidoAbertoManager.getInstance().enviarPedido();
                            Toast.makeText(getActivity(), "Pedido enviado para cozinha.", Toast.LENGTH_SHORT).show();
                            handleConfirmButtonText(confirmarButton);
                            handleConfirmButtonClickEvent(confirmarButton, valorTotal, total );

                            super.onPositiveActionClicked(fragment);
                        }
                    };

                    builder.title("Confirmar Pedido").positiveAction("OK").negativeAction("CANCELAR");

                    DialogFragment fragment = DialogFragment.newInstance(builder);
                    fragment.show(getFragmentManager(), null);
                }
            });
        }
        else{
            confirmarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity());
                    builder.customView(R.layout.dialog_pagamento, true);
                    builder.title("Pagamento").positiveText("OK").negativeText("CANCELAR");
                    builder.callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            pedidoAdapter.clear();
                            PedidoAbertoManager.getInstance().pagaConta();
                            handleConfirmButtonText(confirmarButton);
                            final String valorTotalZerado = PedidoAbertoManager.getInstance().getTotal();
                            total.setText(valorTotalZerado);
                            handleConfirmButtonClickEvent(confirmarButton, valorTotalZerado, total);
                            Toast.makeText(getActivity(), "Pagamento realizado com sucesso", Toast.LENGTH_SHORT).show();
                            super.onPositive(dialog);
                        }
                    });

                    MaterialDialog dialog = builder.build();

                    TextView dialogTotal = (TextView)dialog.getCustomView().findViewById(R.id.dialog_pagamento_total);
                    dialogTotal.setText(valorTotal);
                    dialog.show();
                }
            });

        }
    }

}
