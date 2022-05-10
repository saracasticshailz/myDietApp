package com.d4h.hp.diet4happlication.AllActivities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* import com.example.shivam.studentportal.P;
        import com.example.shivam.studentportal.R;
        import com.example.shivam.studentportal.ServerError;
        import com.example.shivam.studentportal.URL;
        import com.example.shivam.studentportal.VolleyRequestQueue;
        import com.example.shivam.studentportal.subfragments.quickLinks.AdministrativeManagement.Suggestion;
        import com.itextpdf.text.Document;
        import com.itextpdf.text.Image;
        import com.itextpdf.text.pdf.PdfWriter;
*/

/**
 * Created by Shivam on 18/05/03.
 */

public class PaymentSuccessActivity extends AppCompatActivity {
    TextView txtStdName1,txtStdId1,txtStdEmail1,txtTxnId1,txtAmount1,txtDateTime1,txtPaymentId;
    String stdName1,stdId1,stdemail1,txnId1,amount,dirpath,paymentid;
    Calendar calendar1;
    Context context;
    int txnid;
    String dbname;
    SharedPreferences sp,sp1;
    String status = "Success";
    Float amt;
    String dateTime;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_success_paymnent);
        context = getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

      /*  txtStdName1 = (TextView) findViewById(R.id.student_name1);
        txtStdId1 = (TextView) findViewById(R.id.student_id1);
        txtStdEmail1 = (TextView) findViewById(R.id.student_email1);
        txtTxnId1 = (TextView) findViewById(R.id.transaction_id1);
        txtAmount1 = (TextView) findViewById(R.id.txnAmount1);
        txtDateTime1 = (TextView) findViewById(R.id.payment_date1);
        txtPaymentId = (TextView) findViewById(R.id.success_payment_id);
*/

        calendar1 = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateTime = dateFormat.format(calendar1.getTime());

        SharedPreferences pref1 = getSharedPreferences("pref", Context.MODE_PRIVATE);
        // SharedPreferences sp1 = getSharedPreferences("txnid",MODE_PRIVATE);
        sp = getSharedPreferences("paymnetid",MODE_PRIVATE);
        sp1 = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        dbname = sp1.getString("dbName", "none");
        txnid = sp.getInt("txnid",0000);
        paymentid = sp.getString("paymentid","0000");
        // txnId1 = sp1.getString("txnid","XXXX");
        stdName1 = pref1.getString("sname","XXX");
        stdId1 = pref1.getString("sid","xxx");
        stdemail1 = pref1.getString("eid","xxx@gmail.com");
        amt = pref1.getFloat("amt",1.1f);


        txtStdName1.setText(stdName1);
        txtStdId1.setText(stdId1);
        txtStdEmail1.setText(stdemail1);
        // txtAmount.setText(""+amt);
        txtAmount1.setText(""+amt);
        // txtTxnId1.setText(txnId1);
        txtDateTime1.setText(dateTime);
        txtPaymentId.setText(paymentid);
        txtTxnId1.setText(""+txnid);

        // vollyPaymentReceiptS(dbname,stdName1,stdId1,txnid,paymentid,amt,status,stdemail1,dateTime);
        //  Log.d("Data:",dbname+"--"+stdName1+"--"+stdId1+"--"+txnid+"--"+paymentid+"--"+amt+"--"+status+"--"+stdemail1+"--"+dateTime);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        ////inflater.inflate(R.menu.failure_success_download_icon,menu);
        return super.onCreateOptionsMenu(menu);
    }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.download_now){
            //Toast.makeText(this, "Hiiiiii", Toast.LENGTH_SHORT).show();
            takeScreenshot();
            try {
                imageToPDF();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else if(item.getItemId() == R.id.share_now){
            // Bitmap bitmap1 = loadBitmapFromView(rl_main, rl_main.getWidth(), rl_main.getHeight());
            // saveBitmap(bitmap1);
            takeScreenshot();
            String str_screenshot = "/sdcard/Testing/"+"payment" + ".jpg";
            fn_share(str_screenshot);

        }
        return super.onOptionsItemSelected(item);
    }*/

    // fro taking screenshot
   /* private void takeScreenshot() {
        //  Date now = new Date();
        //  android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + "payment" + ".jpg";
            // String mPath = "/sdcard/Testing/"+"payment" + ".jpg";
            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            // Toast.makeText(this, "Screen shot captured.", Toast.LENGTH_SHORT).show();
            // openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }
    // convert image to pdf
    public void imageToPDF() throws FileNotFoundException {
        try {
            Document document = new Document();
            dirpath = android.os.Environment.getExternalStorageDirectory().toString();
            PdfWriter.getInstance(document, new FileOutputStream(dirpath + "/payment.pdf")); //  Change pdf's name.
            document.open();
            Image img = Image.getInstance(Environment.getExternalStorageDirectory() + File.separator + "payment.jpg");
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin() - 0) / img.getWidth()) * 100;
            img.scalePercent(scaler);
            img.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);
            document.add(img);
            document.close();
            Toast.makeText(this, "your payment status download successfully!..", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
    }*/
    public void fn_share(String path) {

        File file = new File("/mnt/" + path);
        Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_TEXT,"Paymet_details");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, "Share Image"));
    }
/*
    public void vollyPaymentReceiptS(final String dbName, String studName, final String studId, int txnId,String paymentId,float fee, String status, String email,String date){
        RequestQueue queue =Volley.newRequestQueue(this);
        Map<String, String> jsonParam = new HashMap<>();
        jsonParam.put("dbName",dbName);
        jsonParam.put("studName",studName); //1
        jsonParam.put("studId",studId); //2
        jsonParam.put("txnId", String.valueOf(txnId));
        jsonParam.put("paymentId",paymentId); //3
        jsonParam.put("fee", String.valueOf(fee));
        jsonParam.put("status",status); //4
        jsonParam.put("email",email); //5
        jsonParam.put("date",date);
        // Log.d("JsonParam",jsonParam.get("dbname")+" "+jsonParam.get("stdName")+" "+jsonParam.get("stdId")+" "+jsonParam.get("paymentid")+" "+jsonParam.get("status")+" "+jsonParam.get("stdemail"));

        final String bigmethod = Thread.currentThread().getStackTrace()[2].getMethodName();
        final String classname = Suggestion.class.getName();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL.URLPAYMENTRECEIPT, new JSONObject(jsonParam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String msg = response.getString("message");
                            if(msg.equals("success")){
                                //  Log.d("msg:",response.toString());
                                //  Toast.makeText(PaymentFailureActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String smallmethod = Thread.currentThread().getStackTrace()[2].getMethodName();
                String error1 = error.getMessage();
                // ServerError.saveError(stdId, dbname, classname, bigmethod, smallmethod, error1);
                //  Toast.makeText(PaymentSuccessActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("VollyError:",error.toString()+" "+smallmethod.toString());
                P.printToastServerError(context);
            }
        });
        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                Toast.makeText(PaymentSuccessActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                //Log.d("VollyError1:",error.toString());
            }
        });
        queue.add(request);
    }*/
}
