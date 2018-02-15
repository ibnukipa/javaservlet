<div class="ui ${param.position} nomargin orange segment breadcrumb borderradiusless bordernone nopadding">
    <div class="ui left pointing dropdown" style="padding: .78571429em 1em">
        <i class="user circle icon"></i>
        ${sessionScope.user.name}
        <i class="caret right icon"></i>
        <div class="menu" style="width: 100%">
            <a href="/account?id=${sessionScope.user.id}" class="item">
                <i class="user circle icon"></i>
                Account</a>
            <a href="#" class="ui divider"></a>
            <a onclick="document.getElementById(${sessionScope.user.id}+'logoutform').submit()" class="item">
                <i class="unlock icon"></i>
                Logout</a>
        </div>
        <form id="${sessionScope.user.id}logoutform" action="/logout" method="post"></form>
    </div>
</div>