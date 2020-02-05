<template>

    <v-container>
        <p>현재 진행 중인 공고</p>
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
        name: "userscraplist",
        data() {
            return {currentRecruits: [
                    {}
                ], pagenum: 1, pagesize: 5}
        },
        computed: {
            pagecount() {
                let len = this.currentRecruits.length
                let listsize = this.pagesize
                let page = Math.floor(len / listsize)
                return page
            },
            pageData() {
                const start = this.pagenum * this.pagesize - 5,
                    end = start + this.pagesize;
                return this
                    .currentRecruits
                    .slice(start, end);
            }

        },
        created() {
            this.setRecruit()

        },
        methods: {
            setRecruit() {
                axios
                    .get(`http://192.168.31.54:8197/itda/api/getWantedByRecent/`)
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
                                this
                                .currentRecruits
                                .splice(idx, 0, {wid, wantedTitle})
                        }

                    })
                    .catch(() => {})

                },
            nextPage() {
                this.pagenum += 1;
            },
            prevPage() {
                this.pagenum -= 1;
            }
        }

    }
</script>

<style></style>