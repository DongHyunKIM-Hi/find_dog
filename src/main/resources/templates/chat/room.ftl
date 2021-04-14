<!doctype html>
<html lang="en">
<head>
    <title>Websocket Chat</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/dist/css/bootstrap.min.css">
    <style>
        [v-cloak] {
            display: none;
        }
    </style>
</head>
<body>
<div class="container" id="app" v-cloak>
    <div class="row">
        <div class="col-md-12">
            <h3>당신은 누군가요?</h3>
        </div>
    </div>
    <div class="input-group">
        <div class="input-group-prepend">
            <label class="input-group-text">유저 이름</label>
        </div>
        <input type="text" class="form-control" v-model="user_name" @keyup.enter="createRoom">
        <div class="input-group-append">
            <button class="btn btn-primary" type="button" @click="createRoom">입장하기</button>
        </div>
    </div>
</div>
<!-- JavaScript -->
<script src="/webjars/vue/2.5.16/dist/vue.min.js"></script>
<script src="/webjars/axios/0.17.1/dist/axios.min.js"></script>
<script src="/webjars/bootstrap/4.3.1/dist/js/bootstrap.min.js"></script>
<script src="/webjars/sockjs-client/1.1.2/sockjs.min.js"></script>
<script>
    var vm = new Vue({
        el: '#app',
        data: {
            user_name : ''
        },
        created() {
            alert("귀여운 아이들이 새로운 가족을 기다리고 있습니다 ")
        },
        methods: {
            createRoom: function() {
                if("" === this.user_name) {
                    alert("사용자의 이름을 입력해주세요");
                    return;
                } else {
                    var params = new URLSearchParams();
                    params.append("user",this.user_name);
                    axios.post('/chat/room', params)
                        .then(
                            response => {
                                alert(this.user_name + "님 환영합니다")
                                localStorage.setItem("wschat.roomId",response.data)
                                localStorage.setItem("wschat.user_name", this.user_name)
                                location.href="/chat/room/enter/" + response.data;
                            }
                        )
                        .catch( response => { alert("채팅방 개설에 실패하였습니다."); } );
                }
            }
        }
    });
</script>
</body>
</html>