package com.cea436.appteste_searchview_menu_markuptext;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivityTeste extends Activity {

    /*
    * Exemplo de uso de menus e SearchView
    * Busca o texto inserido no SearchView detro dos TextView caso seja encontrada a palavra
    * buscada então grifa a palavra dentro do TextView
    *
    * Highlight All Words that is searched via SearchView into the TextView
    *
    * referencia usada: http://stackoverflow.com/questions/10799732/highlight-all-words-that-is-searched-via-edittext
    */
    TextView texto1, texto2, texto3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_teste);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("CEA 436 - Busca");
        }

        texto1 = (TextView) findViewById(R.id.txt1);
        texto2 = (TextView) findViewById(R.id.txt2);
        texto3 = (TextView) findViewById(R.id.txt3);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla o menu com os botões da action bar
        getMenuInflater().inflate(R.menu.menu_main_activity_teste, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(onSearch());

        return true;
    }

    private SearchView.OnQueryTextListener onSearch() {
        return new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Usuário fez a busca

                if (texto1.getText().toString().contains(query)){

                    marcaTexto(texto1, query);
                }

                if (texto2.getText().toString().contains(query)) {

                    marcaTexto(texto2, query);

                }

                if (texto3.getText().toString().contains(query)) {

                    marcaTexto(texto3, query);

                }


                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                texto1.setText(R.string.texto1);
                texto2.setText(R.string.texto2);
                texto3.setText(R.string.texto3);
                return false;
            }
        };
    }

    public void marcaTexto(TextView tView, String word){

        int ocorrencia = tView.getText().toString().indexOf(word,0);
        Spannable WordtoSpan = new SpannableString( tView.getText() );

        for(int proxPosicao = 0; proxPosicao < tView.getText().toString().length() && ocorrencia!=-1; proxPosicao = ocorrencia+1)
        {


            ocorrencia = tView.getText().toString().indexOf(word,proxPosicao);
            if(ocorrencia == -1)
                break;
            else
            {

                WordtoSpan.setSpan(new BackgroundColorSpan(0xFFFFFF00), ocorrencia, ocorrencia+word.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tView.setText(WordtoSpan, TextView.BufferType.SPANNABLE);
            }

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            toast("Clicou no Search!");
            return true;
        } else if (id == R.id.action_refresh) {
            toast("Clicou no Refresh!");
            return true;
        } else if (id == R.id.action_settings) {
            toast("Clicou no Settings!");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
