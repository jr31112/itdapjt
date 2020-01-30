<template>
    <div >    
        <v-card
            class ="pa-3"
            v-for ="(list,index) in todoList"
            :key="index"
        >
        <p>{{todoList}}</p>
        <v-btn fab flat small color="green">완료</v-btn>
        <v-btn fab flat small color="blue">수정</v-btn>
        <v-btn fab flat small color="red">삭제</v-btn>
        </v-card>
    </div>
</template>
<script>
export default {
    
    props:['todoList'],

    data() {
            return {
            }
        }
        ,
        methods:
        {
         
        }

}
</script>