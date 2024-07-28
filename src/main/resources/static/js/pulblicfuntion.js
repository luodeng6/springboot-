//一共要在layui后引入publicfuntion.js文件，然后调用showUserAvatar方法

const showUserAvatar = (user) => {
    layer.photos({
        photos: {
            "title": "我的头像",
            "start": 0,
            "data": [
                {
                    "alt": user.username + "的头像",
                    "pid": 1,
                    "src": user.profile,
                }
            ]
        }
    });
};

const logout = async () => {
    // 显示确认弹窗
    layer.confirm('确定要退出吗？', {
        btn: ['确定', '取消'] // 按钮
    }, async () => {
        try {
            // 调用退出接口
            const response = await axios.post(`${urlluodeng}/user/logout`);
            const data = response.data;

            if (data.result) {
                layer.msg('退出成功！', {icon: 1});
                // 延迟1秒后跳转到登录页面
                setTimeout(() => {
                    window.location.href = 'login.html';
                }, 1000);
            } else {
                layer.msg('退出失败！', {icon: 2});
            }
        } catch (error) {
            console.error('网络错误：', error);
            layer.msg('网络错误！', {icon: 2});
        }
    });
};


// 获取路由参数
const getRouterParams = () => {
// 获取当前 URL
    const currentUrl = window.location.href;
// 使用 URL 对象来解析 URL
    const url = new URL(currentUrl);
// 使用 URLSearchParams 解析查询参数
    const params = new URLSearchParams(url.search);
    /*// 获取 id 参数
        const id = params.get('id');
        console.log(id); // 输出 32*/
    return params;
}

//格式化日期
const formatDate = (isoString) => {
    const date = new Date(isoString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // getMonth() 返回值为 0-11
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    return `${year}-${month}-${day} ${hours}:${minutes}`;
}
