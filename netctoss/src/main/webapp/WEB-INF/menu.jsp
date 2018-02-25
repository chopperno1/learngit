<%@page pageEncoding="utf-8"%>
<!-- 此jsp被其他所有网页所复用,
因此无法确定当前用户正在访问的网页是谁,
所以此处不适合用相对路径,改用绝对路径. -->
<ul id="menu">
    <li><a href="/netctoss/toIndex.do" class="index_off"></a></li>
    <li><a href="" class="role_off"></a></li>
    <li><a href="" class="admin_off"></a></li>
    <li><a href="/netctoss/findCost.do" class="fee_off"></a></li>
    <li><a href="" class="account_off"></a></li>
    <li><a href="" class="service_off"></a></li>
    <li><a href="" class="bill_off"></a></li>
    <li><a href="" class="report_off"></a></li>
    <li><a href="" class="information_off"></a></li>
    <li><a href="" class="password_off"></a></li>
</ul>