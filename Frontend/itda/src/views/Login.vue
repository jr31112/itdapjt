<template>
<v-dialog :v-model="dialog" opacity="0.5" z-index="5"> 
	<v-container fill-height style="width:520px" >
		<!-- 컨테이너 크기를 500으로해 해주고, fill-heigt를 통해서아래 align center를 이용할 수 있게 됨. -->
		<v-layout align-center row wrap>
			<v-flex xs12>
				<v-alert class = "mb-1"
				:value ="isLoginError" 
				type="error"
				>
    			아이디와 비밀번호를 확인해주세요.
    			</v-alert>
				<v-alert class = "mb-1"
				:value ="isLogin" 
				type="success"
				>
    			로그인이 완료되었습니다1.
    			</v-alert>
				<v-card>
					<v-toolbar flat>
						<!-- flat은 toolbar의 속성이고 아래 그림자 없애주는건가.. 뭐지..?  -->
						<v-toolbar-title>Login</v-toolbar-title>
						<v-btn
         			   	color="green darken-1"
            			text
           				 @click="dialog = false"
          				>
         				Close
		  	            </v-btn>
					</v-toolbar>
					<div class="pa-4" style ="width: 500px">
						<!-- pa는 패딩오토 -->
						<v-text-field v-model="email" label="email을 입력하세요">
						</v-text-field>
						<v-text-field
							v-model="password"
							type ="password"
							label="password를 입력하세요"
						>
						</v-text-field>
						<v-btn class = "mb-1" color="primary" 
						depressed 
						block 
						large 
						@click="login({email,password})"
							>로그인
						</v-btn
						>
						<v-btn class = "mb-1" depressed block large>회원가입</v-btn>
						<!-- store.js에 signin값에 email과 password를 보낸다는 말이된다. -->
						<!-- depressed 쉐도우 없애줌, block 한줄을 꽉차게 가져감, large는 조금 더 크게 만듬 -->
						<!-- <v-btn @click="test">get테스트</v-btn>
						<v-btn @click="test1">Post테스트</v-btn> -->
					</div>
				</v-card>
			</v-flex>
		</v-layout>
	</v-container>
</v-dialog>
</template>
<script>
import { mapState, mapActions} from 'vuex'; 
import axios from "axios"
//const axios = require('axios');

export default {
	data() {
		return {
			email: null,
			password: null,
		};
	},
	computed:
	{
		//이거는 store.js->Login.vue에서 반영. 
		...mapState(["isLogin", "isLoginError"])
	},
	methods: {
		...mapActions(["login"]),
		// test()
		// {	
		// 	//get 뒤에 우리가 쓸 주소를 통해서 얻어온다. 
		// 	axios
		// 	.get("https://reqres.in/api/users?page=2"
		// 	)
		// 	.then(res=>{
		// 		console.log(res);
		// 	})
		// 	.catch(err=> {
		// 		console.log(err);
		// 	})
		// 	.then(()=> {
		// 		console.log("test")
		// 	});
		// 	//then은 성공이든 실패든 수행한다. 	
		// },
		// test1() //post_test
		// {	
		// 	//첫번째 인자에 주소가 들어간다.
		// 	//두번째 인자에는 정보를 담아서 보낸다. 	
		// 	// 근데 왜 에러나지..
		// 	axios
		// 	.post('https://reqres.in/api/register', {
		// 		email: "sydney@fife"
		// 		})
		// 		.then(res=> {
		// 			console.log(res);
		// 		})
		// 		.catch(err=> {
		// 			console.log(err);
		// 		});
		// 	//then은 성공이든 실패든 수행한다. 	
		// }
	}
};
</script>
