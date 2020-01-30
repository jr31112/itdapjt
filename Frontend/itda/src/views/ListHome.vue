<template>
    <v-container>
        <v-layout row wrap> 
            <v-flex xs6 pa-2>
            <List
                :todoList="todoList"
            />
            </v-flex>
        <v-flex xs6 pa-2>
            <ListAdd
            @listadd="listadd"
            />
        </v-flex>
         </v-layout> 
    </v-container>
</template>

<script>
import List from '../components/List.vue'
import ListAdd from '../components/ListAdd.vue'
export default {
    components:
    {
        List, 
        ListAdd
    }
    , 
    data() {
            return {
                todoList: []
            }
        }
        ,
        methods:
        {
            listadd(memo)
            {
                this.todoList.push({memo: memo, status: "created"}); //data에 있는 투두리스트에 더한다.
            }
        }
}
</script>