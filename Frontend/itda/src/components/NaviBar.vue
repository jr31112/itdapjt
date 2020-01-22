<template>
        <header
            class="v-sheet v-sheet--tile theme--dark v-toolbar v-app-bar v-app-bar--clipped v-app-bar--fixed v-app-bar--is-scrolled primary"
            id="documentation-app-bar"
            data-booted="true"
            style="height: 64px; margin-top: 0px; transform: translateY(0px); left: 0px; right: 0px;">
            <div class="v-toolbar__content" style="height: 64px;">
                <button
                    type="button"
                    class="v-app-bar__nav-icon hidden-lg-and-up v-btn v-btn--flat v-btn--icon v-btn--round theme--dark v-size--default">
                    <span class="v-btn__content">
                        <i aria-hidden="true" class="v-icon notranslate mdi mdi-menu theme--dark"></i>
                    </span>
                </button>
                <a
                    href="/"
                    class="d-flex align-center router-link-active"
                    aria-label="It da"
                    title="It da">
                    <div
                        role="img"
                        aria-label="Vuetify Logo"
                        class="v-responsive v-image shrink mr-2"
                        style="width: 100px;">
                        <div class="v-responsive__sizer" style="padding-bottom: 114.132%;"></div>
                        <div
                            class="v-image__image v-image__image--contain"
                            style="background-image: url( https://postfiles.pstatic.net/MjAyMDAxMjFfMjk5/MDAxNTc5NTk1OTk3Mzc1.EBmMpx9wKwBHAV6ElSyFWYngGCuPpPcCgHHv-XxaaSEg.AmDumjW_5PnaQKYwqR-jEXkmNAgue8HPMABXjnu2eDcg.PNG.koo_m/fe.png?type=w966); background-position: center center;"></div>
                        <div class="v-responsive__content" style="width: 1118px;"></div>
                    </div>
                    <!-- <div
                        role="img"
                        aria-label="Vuetify Name"
                        class="v-responsive v-image shrink mt-1 hidden-sm-and-down"
                        style="min-width: 40px; width: 100px;">
                        
                        <div class="v-responsive__sizer" style="padding-bottom: 29.878%;"></div>
                        <div
                            class="v-image__image v-image__image--contain"
                            style="background-image: url(https://postfiles.pstatic.net/MjAyMDAxMjFfMTE5/MDAxNTc5NTk1MTc1MDYy.h_8XqFWZlnqK7kTbiY8NEeKFyVAIXngia_MYGDeCl7Eg.qGl_-nj2NPmjih8sNdZt7XplORK2lePOrpfcNlqkl4Qg.PNG.koo_m/itda.png?type=w966); background-position: center center;"></div>
                        <div class="v-responsive__content" style="width: 328px;"></div>
                    </div> -->
                </a>
                <div class="spacer"></div>
                <div class="v-responsive mr-0 mr-md-6 hidden-xs-only" style="max-width: 300px;">
                    <div class="v-responsive__content">
                        <div
                            class="v-input v-input--hide-details v-input--dense theme--dark v-text-field v-text-field--single-line v-text-field--solo v-text-field--solo-inverted v-text-field--solo-flat v-text-field--is-booted v-text-field--enclosed v-text-field--rounded">
                            <div class="v-input__control">
                                <div class="v-input__slot">
                                    <div class="v-input__prepend-inner">
                                        <div class="v-input__icon v-input__icon--prepend-inner">
                                            <i aria-hidden="true" class="v-icon notranslate mdi mdi-magnify theme--dark"></i>
                                        </div>
                                    </div>
                                    <div class="v-text-field__slot">
                                        <label for="search"
                                            class="v-label theme--dark"
                                            style="left: 0px; right: auto; position: absolute;">기업을 검색해주세요</label>
                                        <input
                                            id="search"
                                            type="text"
                                            class="ds-input"
                                            autocomplete="off"
                                            spellcheck="false"
                                            role="combobox"
                                            aria-autocomplete="list"
                                            aria-expanded="false"
                                            aria-label="search input"
                                            aria-owns="algolia-autocomplete-listbox-0"
                                            dir="auto"
                                            style="">
                                            <pre aria-hidden="true" style="position: absolute; visibility: hidden; white-space: pre; font-family: Roboto, sans-serif; font-size: 16px; font-style: normal; font-variant: normal; font-weight: 400; word-spacing: 0px; letter-spacing: normal; text-indent: 0px; text-rendering: auto; text-transform: none;"></pre>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <a href="https://www.naver.com"
                        target="_blank"
                        class="hidden-xs-only v-btn v-btn--flat v-btn--text theme--dark v-size--default"
                        aria-label="Store"
                        rel="noopener"
                        style="min-width: 48px;">
                        <span class="v-btn__content">
                            <span class="v-badge v-badge--left theme--dark">
                                <span class="subtitle-1 text-capitalize font-weight-light">
                                    <div class="v-markdown">
                                        <span class="mr">JoB Korea</span>
                                    </div>
                                </span>
                                <span class="v-badge__wrapper">
                                    <span
                                        aria-atomic="true"
                                        aria-label="Badge"
                                        aria-live="polite"
                                        role="status"
                                        class="v-badge__badge red"
                                        style="bottom: calc(100% - 4px); left: auto; right: calc(100% - 4px); top: auto; display: none;">
                                        <i aria-hidden="true" class="v-icon notranslate mdi mdi-tag-text theme--dark"></i>
                                    </span>
                                </span>
                            </span>
                            <i
                                aria-hidden="true"
                                class="v-icon notranslate v-icon--right mdi mdi-open-in-new theme--dark"></i>
                        </span>
                    </a>
                    <a>
                    <v-btn
                        v-on:click="goTotalWantedPage()" class="hidden-sm-and-down v-btn v-btn--flat v-btn--router v-btn--text theme--dark v-size--default"
                        aria-label="For Enterprise"
                        style="min-width: 48px;">
                        <span class="v-btn__content">
                            <span class="subtitle-1 text-capitalize font-weight-light">
                                <div class="v-markdown">
                                     <span class="mr">상세보기</span>
                                </div>
                            </span>
                        </span>
                    </v-btn>
                    </a>
                    <div class="v-menu">
                        <div
                            role="menu"
                            class="v-menu__content theme--light "
                            style="max-height: calc(100% - 16px); min-width: 0px; top: 12px; left: 12px; transform-origin: left top; z-index: 0; display: none;"></div>
                    </div>
                   
                    <div class="v-menu">
                        <div
                            role="menu"
                            class="v-menu__content theme--light "
                            style="max-height: calc(100% - 16px); min-width: 0px; top: 12px; left: 12px; transform-origin: left top; z-index: 0; display: none;"></div>
                    </div>
                    <button
                        type="button"
                        class="v-btn v-btn--flat v-btn--text theme--dark v-size--default"
                        aria-label="Support"
                        style="min-width: 48px;">
                        <span class="v-btn__content">
                            <span class="subtitle-1 text-capitalize font-weight-light hidden-sm-and-down">
                                <div class="v-markdown">
                                    <span class="mr">로그인/회원가입</span>
                                </div>
                            </span>
                            <i
                                aria-hidden="true"
                                class="v-icon notranslate hidden-sm-and-down v-icon--right mdi mdi-menu-down theme--dark"></i>
                            <i
                                aria-hidden="true"
                                class="v-icon notranslate hidden-md-and-up mdi mdi-comment-question theme--dark"></i>
                        </span>
                    </button>
                </div>
                
            </header>
    </template>
    <script>
    import router from "../router/index.js"

    export default {
        components:{
            
        },
        methods: {
            getImgUrl(img) {
                return require("../assets/" + img);
            },
            goTotalWantedPage(){
                router.push({name:'totalwanted'}).catch(() => {});
            }
        }
    };
</script>