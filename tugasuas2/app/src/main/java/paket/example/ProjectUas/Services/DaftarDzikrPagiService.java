package paket.example.ProjectUas.Services;

import paket.example.ProjectUas.Models.DaftarDzikirPagi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DaftarDzikrPagiService {
    @GET("/bluohazard/doa-harian-muslim-server/dzikir_pagi")
    Call<List<DaftarDzikirPagi>> getDzikirPagi();
}
