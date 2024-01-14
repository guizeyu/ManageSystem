import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        currentPathName: ''
    },
    mutations: {
        setPath (state) {
            state.currentPathName = localStorage.getItem("currentPathName")
        }//    这里dependency必须用4以下版本，否则读不到state
    }
})

export default store
