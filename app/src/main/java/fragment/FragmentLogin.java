package fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.techja.fodenew.R;
import com.techja.fodenew.activity.HomeActivity;
import com.techja.fodenew.activity.MySharedPreferences;

import api.APIFode;
import api.DataLogin;
import api.ResponseLoginData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLogin extends Fragment {
    //edt_userPassWord_Login
    private static final String KEY="PUT TOKEN";//c2 tạo key để truyền vào mysharedpefrence
    private TextView textView;
    public static String token;//đây là c1, tạo static để truy cập từ màn khác cùng packit
    private Button btnLoginMain;//nút login
    private TextInputLayout textinputLayoutUserName;
    private TextInputEditText edtUserName;

    private TextInputEditText edtPassWordLogin;
    private TextInputLayout textInputLayoutPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView=view.findViewById(R.id.tv_send_singup);
        //khi nhấn tv_send_singup sẽ chuyển tới fragment singup
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View view) {
                //addToBackStack gúp quay lại màn trước
                FragmentSingUp fragmentSingUp=new FragmentSingUp();
                getParentFragmentManager().beginTransaction()
                        .addToBackStack("1")
                        .replace(R.id.layout_holder,fragmentSingUp)
                        .commit();
            }
        });

        //call api
        textinputLayoutUserName=view.findViewById(R.id.inputedt_username);
        edtUserName=view.findViewById(R.id.edt_username);

        textInputLayoutPassword=view.findViewById(R.id.inputedt_password);
        edtPassWordLogin=view.findViewById(R.id.edt_password);
        btnLoginMain=view.findViewById(R.id.btn_Login_main);
        btnLoginMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiLogin();
            }
        });
    }

    public void ApiLogin() {
        APIFode.retrofit.login(edtUserName.getText().toString().trim(),
                edtPassWordLogin.getText().toString().trim())
                .enqueue(new Callback<ResponseLoginData>() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onResponse(Call<ResponseLoginData> call, Response<ResponseLoginData> response) {
                        if (response.isSuccessful()){
                            //kiêiểm tra 200 trả về thì th tiếp
                            ResponseLoginData responseLoginData=response.body();
                            if (responseLoginData !=null && responseLoginData.getStatus()){
                                //có data và status true thì th tiếp
                                //lấy token và có thể lưu trữ  vào sharedReferences để sử dụng sau này
                                DataLogin dataLogin=responseLoginData.getData();
                                 token =dataLogin.getToken();
                                      //put token
                                 MySharedPreferences mySharedPreferences=new MySharedPreferences(getContext());
                                 mySharedPreferences.PutToken(KEY,token);
                                Log.d("huhu" ,"onResponse: " + token);

                                //token a lấy ở đây nhe

                                Toast.makeText(getContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                                //chuyển tới màn home
                                Intent intent=new Intent(getActivity(), HomeActivity.class);
                                startActivity(intent);


                            }else {
                                    textInputLayoutPassword.setHelperText("Sai mật khẩu,hãy kiểm tra lại");
                            }
                        }else {
                            // Xử lý trường hợp không thành công (nếu có lỗi ở phía server hoặc mã trạng thái HTTP không phải 200)
                            Log.d("MainActivity", "onResponse: Đăng ký thất bại (mã trạng thái HTTP: " + response.code() + ")");
                            Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseLoginData> call, Throwable t) {

                    }
                });
    }
}
