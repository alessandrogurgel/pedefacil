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

import com.rey.material.app.SimpleDialog;
import com.rey.material.app.DialogFragment;

import org.michenux.yourappidea.BuildConfig;
import org.michenux.yourappidea.R;
import org.michenux.yourappidea.YourApplication;
import org.michenux.yourappidea.restaurante.cardapio.CardapioAdapter;
import org.michenux.yourappidea.restaurante.cardapio.CardapioContentProvider;

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
        total.setText(PedidoAbertoManager.getInstance().getTotal());

        final FragmentActivity activity = getActivity();

        com.rey.material.widget.Button confirmarButton = (com.rey.material.widget.Button)view.findViewById(R.id.button_confirmar_pedido);
        if (PedidoAbertoManager.getInstance().getItems().isEmpty())
        {
            confirmarButton.setEnabled(false);
            confirmarButton.setBackgroundColor(getResources().getColor(R.color.disable_color));
            confirmarButton.setTextColor(getResources().getColor(R.color.secondary_text));
        }
        else{
            confirmarButton.setEnabled(true);
            confirmarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SimpleDialog.Builder builder = new SimpleDialog.Builder(R.style.Material_App_Dialog_Simple_Light) {
                        public void onPositiveActionClicked(DialogFragment fragment) {
                            Toast.makeText(activity, "Pedido enviado para cozinha.", Toast.LENGTH_SHORT).show();
                            super.onPositiveActionClicked(fragment);
                        }
                    };

                    builder.title("Confirmar Pedido").positiveAction("OK").negativeAction("CANCELAR");

                    DialogFragment fragment = DialogFragment.newInstance(builder);
                    fragment.show(getFragmentManager(), null);
                }
            });
        }

        return view;

    }

}
