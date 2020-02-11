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
    
    export default {
        name: "userscraplist",
        data() {
            return { pagenum: 1, pagesize: 5}
        },
        props: {
            currentRecruits: {
                type: Array
            }
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
        nextPage() {
            this.pagenum += 1;
        },
        prevPage() {
            this.pagenum -= 1;
        }
    }


</script>

<style></style>