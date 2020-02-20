<template>
    <div>
    <v-textarea
        outline
        v-model="memo1"
        label="투두리스트를 입력해주세요"
        value=""
    ></v-textarea>
    <v-btn v-if="mode ==='add'" @click="listAdd()">리스트 추가</v-btn>
    <v-btn v-else @click="listEdit1">리스트 수정</v-btn>
    </div>
</template>
<script>
import {eventBus} from '../main'
export default {
    data()
    {
        return{
            memo1:null,
            idx:null,
            mode:"add"
        }
    }
    ,
    created() //라이프 사이클, 컴포넌트가 생성될때, $on으로 이벤트 버스를 듣는다, 
    {
        //$on( A, B) , B에는 에로우 펑션이 있어야 this를 사용할 수있음.   
        eventBus.$on('listEdit',(memo, index) =>
        {
            this.memo1 = memo
            this.idx = index;
            this.mode = "edit"
            console.log(this.memo1) //이게 안되는이유는 아직 모르겠다.    
        })
    } 
    ,
    methods:
    {
        listAdd()
        {
            if( this.memo1===null)
            {
                alert("리스트를 입력해주세요!!")
            }
            else
            {
                this.$emit("listJW", this.memo1) //부모로 전송. 
                this.memo1 = null;  // 보내고 나면 초기화 해줘야하기 때문에 
            }

        },
        listEdit1()
        {
            if( this.memo1===null)
            {
                alert("리스트를 입력해주세요!!!!!!!!!!!!!!")
                console.log(this.memo1);
                console.log(this.idx)
            }
            else
            {
                this.$emit("listEdit",this.memo1, this.idx)
                this.memo1 =null 
                this.mode = "add"
             
            }
        }
    }

}
</script>