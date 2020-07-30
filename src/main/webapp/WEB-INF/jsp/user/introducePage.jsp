<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- section hero -->
<section class="hero background parallax shadow-dark d-flex align-items-center" id="home" data-image-src="static/user/images/hero.jpg">
    <div class="cta mx-auto mt-2">
        <h1 class="mt-0 mb-4">I’m P1n93r<span class="dot"></span></h1>
        <p class="mb-4">咱们用这个模板吧~这个是用户登录后，可以看到的大致页面，左侧是用户的功能导航栏以及头像，右侧是相应功能的页面。
            我们的右侧页面的可能需要自己编写，我们可以仿照temp.jsp文件内的页面的写法（每一个section就是一个页面），以后我们每写一个页面就创建一个XXXPage.jsp，然后在index.jsp内使用静态include引入创建的jsp就行啦~
            比如现在创建的pageOne.jsp就是一个右侧页面，然后在index.jsp的那个main内引入就行啦~
            这样分治不容易搞得混乱~
        </p>
        <a href="https://p1n93r.github.io/" target="_blank" class="btn btn-default btn-lg mr-3"><i class="icon-grid"></i>My Blog</a>
        <div class="spacer d-md-none d-lg-none d-sm-none" data-height="10"></div>
        <a href="mailto:pin83r@gmail.com" class="btn btn-border-light btn-lg"><i class="icon-envelope"></i>Contact me</a>
    </div>
    <div class="overlay"></div>
</section>
