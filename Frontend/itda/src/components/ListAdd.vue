<template>
    <div class ="fill-height">
        <v-text-field
        outline
        v-model="memo" 
        label= "투두리스트를 입력해주세요." 
        value="">
        </v-text-field>
        <v-btn @click="listadd">리스트 추가</v-btn>
    </div>
</template>s

<script>
    export default {
        data() {
            return {memo: null};
        },
        methods: {
            listadd() {
                if(this.memo === null)
                {
                    alert("안됨")
                    console.log("hi")
                }
                else
                {
                    this.$emit("listadd",this.memo)
                }
            }
        }
    }
</script>
  