var app = new Vue({
    el: '#app',
    data: {
        // 用于展示数据
        data_list: [],
        //用于展示轮播图
        img_list: ['img/1.jpg', 'img/2.jpg', 'img/3.jpg', 'img/4.jpg'],
        //用于展示用户信息
        user: {},
        // 用与展示公告
        gonggao_list: [],
        dialogFormVisible: false,
    },
    created() {
        // 读取登录用户信息
        axios.get(urlluodeng + '/user/getLoginUser').then((response) => {
            if (!response.data.result) {
                alert(response.data.msg);
                window.location.href = 'login.html';
            } else {
                this.user = response.data.userData;
                console.log('读取数据:')
                //用户信息获取成功后，获取数据列表

                axios.get(urlluodeng + '/book/getAllbooks').then((response) => {
                    if (response.data.result) {
                        this.data_list = response.data.data;
                    } else {
                        alert(response.data.msg);
                    }
                }).catch((error) => {
                    alert(error);
                })
                /*  "id": 7,
                      "title": "欢迎新用户:billie加入我们!",
                      "content": "欢迎加入我们的社区，亲爱的用户！我们很高兴您能成为我们的一员。在这里，您将享受到丰富的资源和友好的交流环境。如果有任何问题或需要帮助，请随时联系我们的支持团队。祝您在这里有一个愉快的体验！",
                      2019-07-11 23:06
                      "time": "2024-07-23T15:50:45.000+00:00",
                      "user": "机器人",
                      "important": 4*/
                // 获取公告信息
                axios.get(urlluodeng + '/gonggao/getgonggaotop4').then((response) => {
                    if (response.data.result) {
                        this.gonggao_list = response.data.data;
                    } else {
                        layer.msg("code:500", {icon: 2, time: 2000});
                    }
                }).catch((error) => {
                    layer.msg("网络错误，请稍后再试", {icon: 2, time: 2000});
                })

            }
        });


    },
    methods: {
        closeModal(){
            // 显示验证码弹窗
            $('#email-modal').modal('hide');
        },
        showAi() {
            // 显示弹窗
            $('#email-modal').modal('show');
        },
        goto_gonggao_detail(gonggao) {
            console.log(gonggao);
            window.location.href = 'gonggao-detail.html?id=' + gonggao.id;
        },
        formatDate(isoString) {
            const date = new Date(isoString);
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0'); // getMonth() 返回值为 0-11
            const day = String(date.getDate()).padStart(2, '0');
            const hours = String(date.getHours()).padStart(2, '0');
            const minutes = String(date.getMinutes()).padStart(2, '0');

            return `${year}-${month}-${day} ${hours}:${minutes}`;
        },
        // 跳转到图书详情页
        goto_book_detail(book) {
            console.log(book);
        },
        // 加入购物车
        add_to_cart(book) {
            console.log(book);
            layer.msg(`《${book.bookname} 》 加入购物车成功！`, {icon: 1});
        },
        show_alter_info() {
            showUserAvatar(this.user);
        },
        logout() {
            logout();
        }


    }
});
