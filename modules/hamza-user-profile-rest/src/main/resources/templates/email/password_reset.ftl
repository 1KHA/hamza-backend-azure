<#if lang == "ar">
<div dir="rtl">
<p>مرحباً ${userName!""}،</p>
<p>تلقينا طلباً لإعادة تعيين كلمة المرور الخاصة بك.</p>
<p>اضغط على الرابط أدناه لتعيين كلمة مرور جديدة:</p>
<p><a href="${resetLink!""}">${resetLink!""}</a></p>
<p>هذا الرابط صالح لمدة ${expiryMinutes!""} دقيقة.</p>
<p>إذا لم تطلب إعادة تعيين كلمة المرور، يرجى تجاهل هذا البريد الإلكتروني.</p>
</div>
<#else>
<p>Hello ${userName!""},</p>
<p>We received a request to reset your password.</p>
<p>Click the link below to set a new password:</p>
<p><a href="${resetLink!""}">${resetLink!""}</a></p>
<p>This link is valid for ${expiryMinutes!""} minute(s).</p>
<p>If you did not request a password reset, please ignore this email.</p>
</#if>
