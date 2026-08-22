<#if lang == "ar">
<div dir="rtl">
<p>مرحباً ${userName!""}،</p>
<p>استخدم رمز التحقق أدناه لإكمال تسجيل الدخول:</p>
<p style="font-size:24px;font-weight:bold;letter-spacing:3px;">${otp!""}</p>
<p>هذا الرمز صالح لمدة ${expiryMinutes!""} دقيقة. لا تشاركه مع أي شخص.</p>
<p>إذا لم تحاول تسجيل الدخول، يرجى تجاهل هذا البريد الإلكتروني.</p>
</div>
<#else>
<p>Hello ${userName!""},</p>
<p>Use the verification code below to complete your sign-in:</p>
<p style="font-size:24px;font-weight:bold;letter-spacing:3px;">${otp!""}</p>
<p>This code is valid for ${expiryMinutes!""} minute(s). Do not share it with anyone.</p>
<p>If you did not try to sign in, please ignore this email.</p>
</#if>
