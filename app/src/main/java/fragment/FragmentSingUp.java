package fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.techja.fodenew.R;

import api.APIFode;
import api.RegisterData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSingUp extends Fragment {
    private TextView textView;
    private EditText edtsingup_username,edtsingup_email,edtsingup_phone,edtsingup_password;
    private Button btnSingupToLogin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_singup,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView=view.findViewById(R.id.tv_singup_tologin);
        //khi nhấn tv_send_singup sẽ chuyển tới fragment singup
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentLogin fragmentLogin=new FragmentLogin();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.layout_holder,fragmentLogin)
                        .commit();
            }
        });

        //callapi
        edtsingup_username=view.findViewById(R.id.singup_username);
        edtsingup_username.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_NEXT) {
                    edtsingup_email.requestFocus();
                    return true;
                }
                return false;
            }
        });
        edtsingup_email=view.findViewById(R.id.singup_email);
        edtsingup_phone=view.findViewById(R.id.singup_phone);
        edtsingup_password=view.findViewById(R.id.singup_password);
        btnSingupToLogin=view.findViewById(R.id.btn_singup_tologin);
        btnSingupToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterApi();
            }
        });
    }

    private void RegisterApi() {
        APIFode.retrofit.register(edtsingup_username.getText().toString().trim(),
                edtsingup_email.getText().toString().trim(),
                edtsingup_phone.getText().toString().trim(),
                edtsingup_password.getText().toString().trim())
                .enqueue(new Callback<RegisterData>() {
                    @Override
                    public void onResponse(Call<RegisterData> call, Response<RegisterData> response) {
                        if (response.isSuccessful()){
                            //kiểm tra nếu trả v 200 thì mới thực hiện
                            RegisterData registerData=response.body();
                            if (registerData.getStatus() && registerData != null){
                                //kiểm tra status có true và register không rỗng thì thực  hiện tiếp
                                Toast.makeText(getContext(),"đăng ký thành công ",Toast.LENGTH_SHORT).show();
                                //đăng ký thành công chuyển tới màn login
                                FragmentLogin fragmentLogin=new FragmentLogin();
                                getParentFragmentManager().beginTransaction()
                                        .replace(R.id.layout_holder,fragmentLogin).commit();
                            }else {
                                Toast.makeText(getContext(),"đăng ký thất bại ",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            // Xử lý trường hợp không thành công (nếu có lỗi ở phía server hoặc mã trạng thái HTTP không phải 200)
                            Log.d("MainActivity", "onResponse: thất bại (mã trạng thái HTTP: " + response.code() + ")");
                            Toast.makeText(getContext(), "thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<RegisterData> call, Throwable t) {

                    }
                });
    }
}
