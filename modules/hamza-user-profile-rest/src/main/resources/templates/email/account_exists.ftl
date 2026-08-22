<#if lang == "ar">
<div dir="rtl">
<p>مرحباً ${userName!""}،</p>
<p>تلقينا طلب تسجيل باستخدام عنوان البريد الإلكتروني هذا، ولكن لديك حساب مسجل لدينا بالفعل.</p>
<p>يمكنك تسجيل الدخول من هنا:</p>
<p><a href="${signInLink!""}">${signInLink!""}</a></p>
<p>إذا نسيت كلمة المرور، استخدم خيار "نسيت كلمة المرور" في صفحة تسجيل الدخول.</p>
<p>إذا لم تكن أنت من قام بهذا الطلب، يمكنك تجاهل هذا البريد بأمان.</p>
</div>
<#else>
<p>Hello ${userName!""},</p>
<p>We received a sign-up request using this email address, but you already have an account with us.</p>
<p>You can sign in here:</p>
<p><a href="${signInLink!""}">${signInLink!""}</a></p>
<p>If you forgot your password, use the "Forgot password" option on the sign-in page.</p>
<p>If you did not make this request, you can safely ignore this email.</p>
</#if>
