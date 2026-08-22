<#if lang == "ar">
<div dir="rtl">
<p>مرحباً ${userName!""}،</p>
<p>شكراً لتسجيلك. لتفعيل حسابك وإكمال التسجيل، اضغط على الرابط أدناه:</p>
<p><a href="${activationLink!""}">${activationLink!""}</a></p>
<p>إذا لم تقم بإنشاء هذا الحساب، يرجى تجاهل هذا البريد الإلكتروني.</p>
</div>
<#else>
<p>Hello ${userName!""},</p>
<p>Thanks for registering. To activate your account and complete your sign-up, click the link below:</p>
<p><a href="${activationLink!""}">${activationLink!""}</a></p>
<p>If you did not create this account, please ignore this email.</p>
</#if>
