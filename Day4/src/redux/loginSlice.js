import { createSlice } from "@reduxjs/toolkit";

const loginSlice=createSlice({
  name:'login',
  initialState:{
    userName:"",
    isLoggedin:false
  },
  reducers:{
    loginUser:(state,action)=>{
      state.userName=action.payload;
      state.isLoggedin=true;
    },
    logoutUser:(state)=>{
      state.userName="";
      state.isLoggedin=false;
    }
  },
});
export const {loginUser,logoutUser}=loginSlice.actions;
export default loginSlice.reducer;