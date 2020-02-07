<template>
    <v-card>
        <v-card-title class="justify-center">
        <h4>기업 리스트</h4>
        </v-card-title>
        <v-card-text>
            <v-container  v-scroll:#scroll-target="onScroll">
                <v-row >
                    <v-col cols="6">
                        <v-text-field 
                         v-model="editedItem.company" 
                         label="회사명"
                         @click:
                         ></v-text-field>
                    </v-col>
                    <v-col cols="6" >
                        <v-text-field v-model="editedItem.stack" label="필요기술"></v-text-field>
                    </v-col>
                    <v-col cols="6" sm="6" md="4">
                        <v-text-field v-model="editedItem.s_date" label="시작일"></v-text-field>
                    </v-col>
                    <v-col cols="6" sm="6" md="4">
                        <v-text-field v-model="editedItem.f_date" label="마감일"></v-text-field>
                    </v-col>
                    <v-col cols="6" sm="6" md="4">
                        <v-text-field v-model="editedItem.rate" label="싸피인취업현황"></v-text-field>
                    </v-col>
                </v-row>
                <v-col>
                    <v-text-field v-model="editedItem.rate" label="싸피인취업현황"></v-text-field>
                </v-col>
            </v-container>
        </v-card-text>
         <v-card-title class="justify-center">
            <h4>공고 동록</h4>
        </v-card-title>
        <v-card-text>
            <v-container>
                <v-row >
                    <v-col cols="12" sm="6" md="4">
                        <v-text-field v-model="editedItem.company" label="회사명"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                        <v-text-field v-model="editedItem.stack" label="필요기술"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                        <v-text-field v-model="editedItem.s_date" label="시작일"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                        <v-text-field v-model="editedItem.f_date" label="마감일"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                        <v-text-field v-model="editedItem.rate" label="싸피인취업현황"></v-text-field>
                    </v-col>
                </v-row>
                <v-col>
                    <v-text-field v-model="editedItem.rate" label="싸피인취업현황"></v-text-field>
                </v-col>
            </v-container>
        </v-card-text>
        <v-card-actions class="justify-center" >
            <v-layout >
                <v-flex>
                    <v-btn color="blue darken-1" text="text" @click="save">Save</v-btn>
                    <v-btn color="blue darken-1" text="text" @click="close">Cancel</v-btn>
                </v-flex >
            </v-layout>
        </v-card-actions>
    </v-card>
    
</template>

<script>
    export default {
        name: "adminrecruitdialog",
        data() {
            return {
                onScroll: false,
                editedIndex: -1,
                editedItem: 
                {
                    company: '',
                    s_date: 0,
                    f_date: 0,
                    rate: 0
                }
            }
        },
        methods: {
            editItem(item) {
                this.editedIndex = this
                    .allwanteds
                    .indexOf(item)
                this.editedItem = Object.assign({}, item)
                this.dialog = true
            },
            deleteItem(item) {
                const index = this
                    .allwanteds
                    .indexOf(item)
                confirm('Are you sure you want to delete this Recruit?') && this
                    .allwanteds
                    .splice(index, 1)
            },
            close() {
                this.dialog = false
                setTimeout(() => {
                    this.editedItem = Object.assign({}, this.defaultItem)
                    this.editedIndex = -1
                }, 300)
            },
            save() {
                if (this.editedIndex > -1) {
                    Object.assign(this.allwanteds[this.editedIndex], this.editedItem)
                } else {
                    this
                        .allwanteds
                        .push(this.editedItem)
                }
            }
        }
    }
</script>
<style></style>