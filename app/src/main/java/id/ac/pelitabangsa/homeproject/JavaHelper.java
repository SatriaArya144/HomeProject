package id.ac.pelitabangsa.homeproject;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.pelitabangsa.homeproject.BaseApp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AJISETYA
 */
public class JavaHelper extends BaseApp {

    /*alamat utama, silahkan disesuaikan dgn ip sobat dan nama folder sobat*/
    public static String BASE_URL = "http://192.168.0.104/loginquery/";

    /*fungsi cek kesamaan text*/
    public static boolean isCompare(EditText et1, EditText et2){
        String a = et1.getText().toString();
        String b = et2.getText().toString();
        /*jika a sama dengan b*/
        if (a.equals(b)){
            return false;
        }else{
            return true;
        }
    }

    /*fungsi untuk menampilkan pesan*/
    public static void pesan (Context c, String msg){
        Toast.makeText(c,msg,Toast.LENGTH_LONG).show();
    }

    /*fungsi untuk mengecek apakah isian kosong*/
    public static boolean isEmpty(EditText editText){
        /*jika banyak huruf lebih dari 0*/
        if (editText.getText().toString().trim().length() > 0){
            /*tidak dikembalikan*/
            return false;
        }else {
            /*kembalikan*/
            return true;
        }
    }

    // validasi untuk inputan email
    public static boolean isEmailValid(EditText email) {
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email.getText().toString();

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}