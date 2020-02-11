<template >
    <v-card>
        <v-layout >
            <v-flex xs9="xs9" ma-3="ma-3">
                <v-btn style="float:right" class="ml-auto" @click.stop="overlay=!overlay">New +</v-btn>
            </v-flex>
            <v-flex xs3="xs3" style="float:right">
                <v-card-title >
                    <v-text-field
                        v-model="search"
                        append-icon="search"
                        label="Search"
                        single-line="single-line"
                        hide-details="hide-details"></v-text-field>
                </v-card-title>
            </v-flex>
            <v-dialog v-model="overlay" scrollable="scrollable" max-width="900px">
                <v-card>
                    <v-card-title>기업 등록</v-card-title>
                    <v-card-text>
                        <v-form ref="form" v-model="valid">
                            <div>
                                <v-text-field
                                    v-model="formData.stname"
                                    :rules="[v => !!v || '회사명을 입력해주세요']"
                                    label="회사명"
                                    @keyup.enter="openPopup(2)"
                                    required="required"></v-text-field>
                                <v-btn @click="openPopup(2)">회사명 찾기</v-btn>
                            </div>
                            <!-- <v-select v-model="formData.sgroup" :items="formCategory.category2"
                            :rules="[v => !!v || '스터디 내용를 선택해주세요']" label="스터디 내용"
                            required="required"></v-select> <v-textarea v-model="formData.content"
                            solo="solo" :rules="[v => !!v || '스터디 정보를 입력해주세요']" label="스터디 정보를
                            입력해주세요"></v-textarea> -->
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="success" text="text" :disabled="!valid" @click="validate">submit</v-btn>
                        <v-btn color="blue darken-1" text="text" @click="reset">Reset</v-btn>
                        <v-btn color="blue darken-1" text="text" @click="close">Close</v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>

        </v-layout>
        <v-data-table
            :headers="headers"
            :items-per-page="5"
            :items="allwanteds"
            :search="search">
            <template v-slot:item.action="{ item }">
                <v-icon small="small" @click="deleteItem(item)">
                    delete
                </v-icon>
            </template>
        </v-data-table>
    </v-card>
</template>
<script>
    import {mapState} from 'vuex'
    import axios from 'axios'
  
    export default {
        name: "adminrecruit",
        mounted() {
            this.getWantedAll()
        },
                methods: {

            getWantedAll() {
                axios
                    .get('https://192.168.31.54:8197/itda/api/getWantedAll/')
                    .then(res => {
                        this.allwanteds = res.data

                    })
                    .catch((err) => {
                        alert(err)
                    })
                },
            validate() {
                var select = JSON.parse(localStorage.getItem('select'))
                if (!select) {
                    alert('스터디 주제를 확인해주세요')
                } else {
                    this.formData.typeFk = select.id
                    this.formData.typeName = select.Nm
                    if (this.$refs.form.validate()) {
                        this.snackbar = true
                        axios
                            .post('https://192.168.31.54:8197/itda/api/createStudy', this.formData, {
                                'headers': {
                                    "jwt-auth-token": localStorage.getItem("access_token")
                                }
                            })
                            .then(() => {
                                alert('스터디를 생성하였습니다.')
                                this
                                    .$refs
                                    .form
                                    .reset()
                                this.overlay = false
                                localStorage.removeItem('select')
                                this.$emit("child")
                                this.getStudies()
                            })
                            .catch()
                        }
                }
            },
            reset() {
                this
                    .$refs
                    .form
                    .reset()
                localStorage.removeItem('select')
            },
            close() {
                this.formData = {
                    stname: '',
                    content: '',
                    maxPcnt: null,
                    stype: null,
                    sgroup: null,
                    typeFk: null,
                    typeName: ''
                },
                this
                    .$refs
                    .form
                    .reset()
                localStorage.removeItem('select')
                this.overlay = false
            },
            openPopup(type) {
                let routeData = this
                    .$router
                    .resolve({
                        name: 'searchdata',
                        params: {
                            type: type
                        }
                    })
                localStorage.setItem('pre', this.formData.stname)
                window.open(routeData.href, '_blank', 'width=500,height=700,')
            },
            deleteItem(item) {
                alert(item)
            }
        },
        computed: {
            formTitle() {
                return this.editedIndex === -1
                    ? 'New Item'
                    : 'Edit Item'
            },
            ...mapState(["isLogin"])
        },
        watch: {
            dialog(val) {
                val || this.close()
            }
        },
        data() {
            return {
                search: '',
                headers: [
                    {
                        text: '공고명',
                        align: 'left',
                        filterable: false,
                        value: 'wanted.wantedTitle'
                    }, {
                        text: '회사명',
                        value: 'company.corpNm',
                        align: 'left'

                    }, {
                        text: '시작일',
                        value: 'wanted.startDate',
                        align: 'center'
                    }, {
                        text: '마감일',
                        value: 'wanted.endDate',
                        align: 'center'
                    }, {
                        text: 'Actions',
                        value: 'action',
                        align: 'center',
                        sortable: false
                    }
                ],
                formData: {
                    stname: '',
                    content: '',
                    maxPcnt: null,
                    stype: null,
                    sgroup: null,
                    typeFk: null,
                    typeName: ''
                },
                formCategory: {
                    category1: [
                        {
                            text: '공고 대비',
                            value: 1
                        }, {
                            text: '기업 대비',
                            value: 2
                        }, {
                            text: '기술 대비',
                            value: 3
                        }, {
                            text: '기타 등등',
                            value: 4
                        }
                    ],
                    category2: [
                        {
                            text: '자소서 준비',
                            value: 1
                        }, {
                            text: '필기 준비',
                            value: 2
                        }, {
                            text: '면접 준비',
                            value: 3
                        }, {
                            text: '기타 등등',
                            value: 4
                        }
                    ]
                },
                allwanteds: [],
                editedIndex: -1,
                editedItem: {
                    company: '',
                    s_date: 0,
                    f_date: 0,
                    rate: 0
                },
                overlay: false
            }
        }
    }
</script>

<style></style>