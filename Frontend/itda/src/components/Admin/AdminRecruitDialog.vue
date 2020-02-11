<template>
    <v-card>
        <v-card-title>기업 등록</v-card-title>
        <v-card-text>
            <v-form ref="form" v-model="valid">
                <v-text-field
                    v-model="formData.stname"
                    :rules="[v => !!v || '스터디 이름을 입력해주세요']"
                    label="스터디명"
                    required="required"></v-text-field>
                <v-text-field
                    v-model.number="formData.maxPcnt"
                    type="number"
                    :rules="[v => !!v || '인원수를 입력해주세요']"
                    label="인원수"
                    required="required"></v-text-field>
                <v-select
                    v-model="formData.stype"
                    :items="formCategory.category1"
                    :rules="[v => !!v || '스터디 종류를 선택해주세요']"
                    label="스터디 종류"
                    required="required"></v-select>
                <v-btn v-if="formData.stype" @click="openPopup(formData.stype)">스터디 주제 입력</v-btn>
                <v-select
                    v-model="formData.sgroup"
                    :items="formCategory.category2"
                    :rules="[v => !!v || '스터디 내용를 선택해주세요']"
                    label="스터디 내용"
                    required="required"></v-select>
                <v-textarea
                    v-model="formData.content"
                    solo="solo"
                    :rules="[v => !!v || '스터디 정보를 입력해주세요']"
                    label="스터디 정보를 입력해주세요"></v-textarea>
            </v-form>
        </v-card-text>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="success" text="text" :disabled="!valid" @click="validate">submit</v-btn>
            <v-btn color="blue darken-1" text="text" @click="reset">Reset</v-btn>
            <v-btn color="blue darken-1" text="text" @click="close">Close</v-btn>
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
                editedItem: {
                    company: '',
                    s_date: 0,
                    f_date: 0,
                    rate: 0
                }
            }
        },
        methods: {
            searchCompanyItem() {},
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