package com.wujie.springbootlayui.sys.common;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @date 2020-08-13
 * @author wuj
 * @desc 阿里云短信平台接口
 */
public class SMSUtil {
	// 产品名称:云通信短信API产品,开发者无需替换
	private static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	private static final String domain = "dysmsapi.aliyuncs.com";

	// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	private static final String accessKeyId = "****************"; // TODO 改这里

	private static final String accessKeySecret = "***************"; // TODO 改这里

	public static SendSmsResponse sendSms(String telephone, String code,int type) {

		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		
		SendSmsResponse sendSmsResponse = null;
		try {
			// 初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);

			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);

			IAcsClient acsClient = new DefaultAcsClient(profile);

			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
	        //使用post提交
	        request.setMethod(MethodType.POST);
			// 必填:待发送手机号
			request.setPhoneNumbers(telephone);
			// 必填:短信模板-可在短信控制台中找到
			 // type 1-修改 2-注册  3-vip  4-黑名单
			switch (type) {
			case 1:
				// 必填:短信签名-可在短信控制台中找到
				request.setSignName("AI人脸识别系统"); // TODO 改这里
				request.setTemplateCode("SMS_192545258");
				// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
				request.setTemplateParam("{\"code\":\"" + code + "\"}");
				break;
            case 2:
            	// 必填:短信签名-可在短信控制台中找到
    			request.setSignName("AI人脸识别系统"); // TODO 改这里
            	request.setTemplateCode("SMS_192545259");
            	// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
    			request.setTemplateParam("{\"code\":\"" + code + "\"}");
				break;
            case 3:
            	// 必填:短信签名-可在短信控制台中找到
    			request.setSignName("springbootLayui系统"); // TODO 改这里
            	request.setTemplateCode("SMS_206890046");
            	request.setTemplateParam("{\"name\":\"" + code + "\"}");
				break;
            case 4:
            	// 必填:短信签名-可在短信控制台中找到
    			request.setSignName("springbootLayui系统"); // TODO 改这里
            	request.setTemplateCode("SMS_206890049");
            	request.setTemplateParam("{\"name\":\"" + code + "\"}");
				break;
			default:
				break;
			}
			// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
			// request.setSmsUpExtendCode("90997");
			// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			//request.setOutId("yourOutId");
			// hint 此处可能会抛出异常，注意catch
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return sendSmsResponse;
	}

	public static void main(String[] args) throws Exception {
		String code = StringUtil.getRandom();
		SendSmsResponse sendSms = sendSms("18565684220", code,1);// 填写你需要测试的手机号码
		System.out.println("短信接口返回的数据----------------");
		System.out.println("Code=" + sendSms.getCode());
		System.out.println("Message=" + sendSms.getMessage());
		System.out.println("RequestId=" + sendSms.getRequestId());
		System.out.println("BizId=" + sendSms.getBizId());
		System.out.println(StringUtil.getRandom());
	}

}
