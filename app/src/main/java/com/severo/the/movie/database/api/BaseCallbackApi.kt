package com.severo.the.movie.database.api

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.severo.the.movie.database.ui.activities.HomeActivity
import com.severo.the.movie.database.util.Util.alert
import com.severo.the.movie.database.util.Util.apiToken
import com.severo.the.movie.database.util.Util.loadingDialog
import com.severo.the.movie.database.ui.activities.ServerErrorActivity
import org.apache.http.conn.ConnectTimeoutException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException

open class BaseCallbackApi<T>(val context: Context) : Callback<T> {
    private var dialog: Dialog? = null

    init {
        OnStartLoading(context)
    }

    override fun onResponse(
        call: Call<T>,
        response: Response<T>
    ) {
        OnStopLoading()
        alertStatusCode(response.code(), response.errorBody()?.string())
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        OnStopLoading()
        whenConnectTimeOut(t)
    }

    private fun alertStatusCode(statusCode: Int, errorBody: String?) {
        if (statusCode == 500) {
            alert(
                context,
                "Falha",
                "Um erro interno ocorreu no servidor, por favor tente mais tarde."
            )
        } else if (statusCode == 404) {
            alert(
                context,
                "Falha",
                "O recurso solicitado não foi encontrado."
            )
        } else if (statusCode == 401 || statusCode == 403) {
            alert(
                context,
                "Falha",
                "Chave de API inválida: você deve receber uma chave válida."
            )
        }else if(statusCode == 400){
            Toast.makeText(context, "Verifique as informações e tente novamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun whenConnectTimeOut(throwable: Throwable) {
        if (throwable is SocketTimeoutException
            || throwable is ConnectTimeoutException
        ) {
            val intent = Intent(context, ServerErrorActivity::class.java)
            intent.putExtra(
                "message",
                "Tempo para conexão esgostado ou servidor indisponível, por favor tente mais tarde."
            )
            context.startActivity(intent)
        }
        if (throwable is ConnectException) {
            val intent = Intent(context, ServerErrorActivity::class.java)
            intent.putExtra(
                "message",
                "Verifique seu acesso a internet ou tente novamente mais tarde."
            )
            context.startActivity(intent)
        }
    }

    private fun OnStopLoading() {
        if (dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

    private fun OnStartLoading(context: Context) {
        dialog = loadingDialog(context)
        dialog!!.show()
    }
}