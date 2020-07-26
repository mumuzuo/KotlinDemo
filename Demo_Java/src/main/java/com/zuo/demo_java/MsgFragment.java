package com.zuo.demo_java;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.zuo.demo_java.databinding.FragmentMsgBinding;

/**
 * @author zuo
 * @date 2020/7/26 22:32
 */
public class MsgFragment extends Fragment {

    private FragmentMsgBinding binding;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg, container, false);
        binding = DataBindingUtil.bind(view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        initView();
        binding.header.back.setOnClickListener(v -> {
            navController.popBackStack();
        });
        binding.btn.setOnClickListener(v -> {
            String trim = binding.et.getText().toString().trim();
            if (TextUtils.isEmpty(trim)) {
                Toast.makeText(this.getContext(), "请先填写需发送的消息！", Toast.LENGTH_SHORT).show();
                return;
            }
            Uri uri = Uri.parse("zuo://www.zuo.com/msg/" + trim);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }

    private void initView() {
        binding.header.title.setText("消息界面");
    }
}