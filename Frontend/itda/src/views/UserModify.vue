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
                <v-dialog v-model="dialog" scrollable="scrollable" max-width="300px">
                    <template v-slot:activator="{ on }">
                        <v-btn
                            class="mx-3"
                            fab="fab"
                            dark="dark"
                            color="gray"
                            width="30px"
                            height="30px"
                            v-on="on">
                            <v-icon dark="dark">mdi-plus</v-icon>
                        </v-btn>
                        <v-icon dark="dark">mdi-plus</v-icon>
                    </template>
                    <v-card>
                        <v-card-title>Select Country</v-card-title>
                        <v-divider></v-divider>
                        <v-card-text style="height: 300px;">
                            <v-checkbox-group v-model="userStacks" column="column">
                                <v-checkbox v-model="userStacks" label="Bahamas, The" value="bahamas"></v-checkbox>
                                <v-checkbox v-model="userStacks" label="Bahrain" value="bahrain"></v-checkbox>
                                <v-checkbox v-model="userStacks" label="Bangladesh" value="bangladesh"></v-checkbox>
                                <v-checkbox v-model="userStacks" label="Barbados" value="barbados"></v-checkbox>
                                <v-checkbox v-model="userStacks" label="Belarus" value="belarus"></v-checkbox>
                                <v-checkbox v-model="userStacks" label="Belgium" value="belgium"></v-checkbox>
                            </v-checkbox-group>
                        </v-card-text>
                        <v-divider></v-divider>
                        <v-card-actions>
                            <v-btn color="blue darken-1" text="text" @click="dialog = false">Close</v-btn>
                            <v-btn color="blue darken-1" text="text" @click="dialog=!dialog">Save</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>

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
            return {
                userStacks: [],
                email: "",
                name: "",
                major: "",
                info: "",
                dialog: false
            }
        },
        props: {
            userInfo: {
                type: Object
            }
        },
        monted() {
            this.setUserInfo()

        },
        methods: {
            setUserInfo() {
                this.email = this.userInfo.user.email
                this.name = this.userInfo.user.uname
                this.major = this.userInfo.user.major
                this.info = this.userInfo.user.cid
            }
        }
    }
</script>

<style></style>