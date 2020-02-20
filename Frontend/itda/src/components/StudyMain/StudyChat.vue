<template>
    <div>

    </div>
</template>

<script >
import axios from 'axios'
import { mapState } from "vuex";
   export default {
        name: "stduydetail",
        data() {
            return {
            }
        },
        props:["study","person"],
        computed: 
        {
            ...mapState([ "isLogin", "isLoginError", "isDialog"])
        },
        mounted()
        {
            console.log(this.study)
            console.log(this.person)
        },
        getDatainFo(stid)
        {
            axios
            .get('https://i02b201.p.ssafy.io:8197/itda/api/getCommentById/'+stid)
            .then(response => {
                console.log(response)
                if (response.data.state == "success") {
                this.userInfo = response.data;

                var tmp_stacks = response.data.mystacks;
                for (var i = 0; i < tmp_stacks.length; i++) {
                this.stacklist[tmp_stacks[i].sid - 1].value = true;
                }
            }
            })
            .catch(() => {});
        }
        
   }
</script>