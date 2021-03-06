package com.chaxin.paylibrary.pay.wxpay;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Sgun on 15/10/30.
 */
public class WxpayManager {
    private PayReq mPayReq;

    private static Context mContext;

    private static WxpayManager mInstance;

    public static WxpayManager getInstance(Context context) {
        if(mInstance == null) {
            mContext = context;
            synchronized (mContext) {
                if(mInstance == null) {
                    mInstance = new WxpayManager();
                }
            }
        }
        return mInstance;
    }

    public void  pay(String body, double price, String tradeNo) {
        GetPrepayIdTask task = new GetPrepayIdTask(body, ((int) (price * 100)) + "", tradeNo);
        task.execute();
    }

    // 获取参数
    private String genProductArgs(String body, String price, String tradeNo) {
        StringBuffer xml = new StringBuffer();

        try {
            String nonceStr = genNonceStr();
            xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", Constants.APP_ID));
            packageParams.add(new BasicNameValuePair("body", body));
            packageParams.add(new BasicNameValuePair("mch_id", Constants.MCH_ID));
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
            packageParams.add(new BasicNameValuePair("notify_url", "http://www.chahuitong.com/mobile/api/payment/wxpay/notify_url.php"));
            packageParams.add(new BasicNameValuePair("out_trade_no", TextUtils.isEmpty(tradeNo) ? genOutTradNo() : tradeNo));
            packageParams.add(new BasicNameValuePair("spbill_create_ip","127.0.0.1"));
            packageParams.add(new BasicNameValuePair("total_fee", price));
            packageParams.add(new BasicNameValuePair("trade_type", "APP"));

            String sign = genPackageSign(packageParams);
            packageParams.add(new BasicNameValuePair("sign", sign));

            String xmlString = toXml(packageParams);

            //改变拼接之后xml字符串格式
            return new String(xmlString.toString().getBytes(), "ISO8859-1");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String genNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    private String genOutTradNo() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    /**
     * 生成签名
     */
    private String genPackageSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);

        return MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
    }

    private String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<"+params.get(i).getName()+">");


            sb.append(params.get(i).getValue());
            sb.append("</"+params.get(i).getName()+">");
        }
        sb.append("</xml>");

        return sb.toString();
    }

    private Map<String,String> decodeXml(String content) {
        try {
            Map<String, String> xml = new HashMap<String, String>();
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(content));
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {

                String nodeName=parser.getName();
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    case XmlPullParser.START_TAG:

                        if("xml".equals(nodeName)==false){
                            //实例化student对象
                            xml.put(nodeName,parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = parser.next();
            }

            return xml;
        } catch (Exception e) {
            Log.e("orion", e.toString());
        }
        return null;
    }


    private void genPayReq(Map<String, String> result) {
        mPayReq = new PayReq();
        mPayReq.appId = Constants.APP_ID;
        mPayReq.partnerId = Constants.MCH_ID;
        mPayReq.prepayId = result.get("prepay_id");
        mPayReq.packageValue = "Sign=WXPay";
        mPayReq.nonceStr = genNonceStr();
        mPayReq.timeStamp = String.valueOf(genTimeStamp());

        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", mPayReq.appId));
        signParams.add(new BasicNameValuePair("noncestr", mPayReq.nonceStr));
        signParams.add(new BasicNameValuePair("package", mPayReq.packageValue));
        signParams.add(new BasicNameValuePair("partnerid", mPayReq.partnerId));
        signParams.add(new BasicNameValuePair("prepayid", mPayReq.prepayId));
        signParams.add(new BasicNameValuePair("timestamp", mPayReq.timeStamp));

        mPayReq.sign = genAppSign(signParams);
//        sb.append("sign\n"+req.sign+"\n\n");
    }

    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);

//        this.sb.append("sign str\n"+sb.toString()+"\n\n");
        String appSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        return appSign;
    }

    private void sendPayReq() {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(mContext, null);
        msgApi.registerApp(Constants.APP_ID);
        msgApi.sendReq(mPayReq);
    }

    private class GetPrepayIdTask extends AsyncTask<Void, Void, Map<String,String>> {
        private String body;
        private String price;
        private String tradeNo;

        public GetPrepayIdTask(String body, String price, String tradeNo) {
            this.body = body;
            this.price = price;
            this.tradeNo = tradeNo;
        }

        @Override
        protected void onPostExecute(Map<String,String> result) {
            genPayReq(result);
            sendPayReq();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Map<String,String> doInBackground(Void... params) {
            String url = String.format("https://api.mch.weixin.qq.com/pay/unifiedorder");
            String entity = genProductArgs(body, price, tradeNo);
            return decodeXml(Util.httpPost(url, entity));
        }
    }
}
