package com.example.waka.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.stripe.android.paymentsheet.flowcontroller.FlowControllerFactory;
import com.stripe.android.paymentsheet.model.PaymentIntentClientSecret;
import com.stripe.android.paymentsheet.model.PaymentOption;
import com.stripe.android.paymentsheet.model.SetupIntentClientSecret;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;
import com.example.waka.Adaptor.CartListAdaptor;
import com.example.waka.Helper.ManagementCart;
import com.example.waka.Interface.ChangeNumberItemsListener;
import com.example.waka.R;
import android.os.Bundle;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.Stripe;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTxt, taxTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;
    TextView button;

    String SECRET_KEY="sk_test_51NSmoyDxu8p9UU5nqLfY9rQ0NmNp6nAmpCRWNybB9tz16QUYgxnFXoaeIPhS7AIUgP1ReDFmcn7pvXXtjxLHeyJC00kZScLNhT";
    String PUBLISH_KEY="pk_test_51NSmoyDxu8p9UU5nBHHIBpEhmFE89hZRWpXyqYUoytL82bv3ly0YnP6pHkUdE784zOutzRRfW6cEd7EXZaOR3Mq800mTRuxpOc";
    PaymentSheet paymentSheet;
    String customerID;
    String EphericalKey;
    String ClientSecret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart=new ManagementCart(this);

        initView();
        initList();
        calculateCart();
        button=findViewById(R.id.pagarbtn);
        PaymentConfiguration.init(this,PUBLISH_KEY);
        paymentSheet=new PaymentSheet(this,paymentSheetResult -> {

        });
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/customers",
                new Response.Listener<String>(){
                @Override
                public void onResponse(String response){

                }
                },
                new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){

            }
        }){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String,String> header=new HashMap<>();
                header.put("Authorization","Bearer"+SECRET_KEY);
                return super.getHeaders();
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(CartListActivity.this);
        requestQueue.add(stringRequest);
    };



    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);
        //LinearLayout homeBtn=findViewById(R.id)
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(CartListActivity.this, CartListActivity.class));

            }
        });
    }
    private void initView(){
        recyclerViewList=findViewById(R.id.recyclerView);
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        totalTxt=findViewById(R.id.totalTxt);
        emptyTxt=findViewById(R.id.emptyTxt);
        scrollView=findViewById(R.id.scrollView3);
        recyclerViewList=findViewById(R.id.cartView);
    }

    private void initList(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdaptor(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }

    }
    private void calculateCart(){
        double percentTax=0.018;
        tax=Math.round((managementCart.getTotalFee()*percentTax)*100)/100;
        double total=Math.round((managementCart.getTotalFee()+tax)*100)/100;
        double itemTotal=Math.round(managementCart.getTotalFee()*100)/100;

        totalFeeTxt.setText("S/."+itemTotal);
        taxTxt.setText("S/."+tax);
        totalTxt.setText("S/."+total);
    }
}