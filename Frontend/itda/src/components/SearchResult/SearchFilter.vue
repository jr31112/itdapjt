<template>
    <v-row>
        <v-col class="pa-3" cols="12" md="6">
            <v-btn-toggle v-model="options.type" :mandatory="true">
                <v-btn color="white">전체 보기</v-btn>
                <v-btn color="white">기업 보기</v-btn>
                <v-btn color="white">기술스택 보기</v-btn>
            </v-btn-toggle>
        </v-col>
        <v-col class="pb-0" cols="12" md="6">
            <v-text-field
                v-model="options.keyword"
                label="키워드"
                placeholder="키워드를 입력해주세요"
                outlined="outlined">
            </v-text-field>
        </v-col>
    </v-row>
</template>
<script>
    
    export default {
        name: "searchfilter",
        props:{
            options:{type:Object}
        },
    }
</script>

<style></style>