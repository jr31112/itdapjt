<template>
    <v-container>
        <p>지난 공고</p>

        <v-card>
            <v-list-item v-for="recruit in pageData" :key="recruit.no">
                <v-list-item-content>
                    <v-list-item-title>{{recruit.wantedTitle}}</v-list-item-title>
                    <v-divider></v-divider>
                </v-list-item-content>

            </v-list-item>
            <div class="text-center">
                <v-pagination
                    v-model="pagenum"
                    :length="pagecount"
                    prev-icon="mdi-menu-left"
                    next-icon="mdi-menu-right"></v-pagination>
            </div>
        </v-card>

    </v-container>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "userendrecruit",
        data() {
            return {endRecruits: [
                    {}
                ], pagenum: 1, pagesize: 5}
        },
        computed: {
            pagecount() {
                let len = this.endRecruits.length
                let listsize = this.pagesize
                let page = Math.floor(len / listsize)
                
                return page
            },
            pageData() {
                const start = this.pagenum * this.pagesize - 5,
                    end = start + this.pagesize;

                return this
                    .endRecruits
                    .slice(start, end);
            }

        },
        created() {
            this.setRecruit()

        },
        methods: {
            setRecruit() {
                axios
                    .get(`https://192.168.31.54:8197/itda/api/getWantedByCloseEnd/`)
                    .then(response => {
                        for (var idx = 0; idx < response.data.length; idx++) {
                            var wid = response
                                .data[idx]
                                .wanted
                                .wid
                            var wantedTitle = response
                                .data[idx]
                                .wanted
                                .wantedTitle
                            //this.currentRecruits.push({wid, wantedTitle})
                            this
                                .endRecruits
                                .splice(idx, 0, {wid, wantedTitle})
                        }

                    })
                    .catch((error) => {
                        console.log(error)
                    })

            }
        }
    }
</script>

<style></style>