<template>
    <v-container>
        <v-card>
            <v-card-title>User Info</v-card-title>
            <v-card-subtitle>User email</v-card-subtitle>
            <v-card-text>
                <v-text-field v-model="email" label="Email"></v-text-field>
            </v-card-text>
            <v-card-subtitle>User Name</v-card-subtitle>
            <v-card-text>
                <v-text-field v-model="name" label="Name"></v-text-field>
            </v-card-text>
            <v-card-subtitle>User Major</v-card-subtitle>
            <v-card-text>
                <v-text-field v-model="major" label="Major"></v-text-field>
            </v-card-text>
            <v-card-subtitle>Recruit Info</v-card-subtitle>
            <v-card-text>
                <v-text-field v-model="info" label="Recruit Info"></v-text-field>
            </v-card-text>
        </v-card>
        <v-divider></v-divider>
        <v-card>
            <v-card-title>Tech Stack
                <v-btn
                    class="mx-3"
                    fab="fab"
                    dark="dark"
                    color="gray"
                    width="30px"
                    height="30px">
                    <v-icon dark="dark">mdi-plus</v-icon>
                </v-btn>

            </v-card-title>
            <v-card-subtitle>My technology stack</v-card-subtitle>
            <v-card-text>java / Spring / python / tomcat / ...</v-card-text>
        </v-card>
        <v-divider></v-divider>
        <v-card>
            <v-card-title>My study Info</v-card-title>
            <v-card-text></v-card-text>
        </v-card>

    </v-container>
</template>

<script>
    export default {
        data() {
            return {email: "", name: "", major: "", info: ""}
        },
        props: {
            userInfo: {
                type: Object
            }
        },
        monted(){
            this.setUserInfo()
            
        },
        methods: {
            setUserInfo() {
                this.email= this.userInfo.user.email
                this.name = this.userInfo.user.uname
                this.major = this.userInfo.user.major
                this.info = this.userInfo.user.cid
            }
        }
    }
</script>

<style></style>