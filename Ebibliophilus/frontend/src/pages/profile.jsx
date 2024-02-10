import "../assets/styles/profile.css";
import CustomNavbar from "../components/navbar";
import { Snackbar } from "@mui/material";
import Alert from "@mui/material/Alert";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import Footer from "../components/footer";
import Name from "../components/name";
import { StarOutline } from "@mui/icons-material";
import Loading from "../components/loading";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Select from 'react-select';
import makeAnimated from 'react-select/animated';
import Button from "react-bootstrap/Button";
import { url } from "../util";

export default function Profile(){
    const [reviews,setReviews]=useState([]);
    const [loaded,setLoaded]=useState(false);
    const userid=useSelector((state)=>state.login.userDetails.id);
    const token=useSelector((state)=>state.login.token);
    const [orders,setOrders]=useState([]);
    const [togglePrefs,setTogglePrefs]=useState(true);
    const nav=useNavigate();
    const islogged=useSelector((state)=>state.login.isLoggedin);
    const [userpref,setPref]=useState([]);
    const [wallet,setWallet]=useState(0);
    const options=[
        {value:"Romance",label:"Romance"},
        {value:"Sad",label:"Sad"},
        {value:"Happy",label:"Happy"},
        {value:"Spy",label:"Spy"},
        {value:"Detective",label:"Detective"},
        {value:"Fantasy",label:"Fantasy"},
        {value:"Adventure",label:"Adventure"},
        {value:"Fiction",label:"Fiction"},
        {value:"Mystery",label:"Mystery"},
        {value:"Fun",label:"Fun"},
        {value:"Young Adult",label:"Young Adult"},
        {value:"Magic",label:"Magic"},
        {value:"Children's",label:"Children's"}
    ]

    const animatedComp=makeAnimated();
    
    const handleSelectChange=(selected)=>{
        setPref(selected);
    }

    const getData=async()=>{
        const walletval=await axios.get(url+"user/wallet/"+userid,{
            headers: {
              Authorization: 'Bearer ' + token
            }
        });
        setWallet(walletval.data);
        const rev=await axios.get(url+"user/getUserReviews/"+userid,{
            headers: {
              Authorization: 'Bearer ' + token
            }
        });
        setReviews(rev.data);
        const pref=await axios.get(url+"user/getPrefs/"+userid,{
            headers: {
              Authorization: 'Bearer ' + token
            }
        });
        const ord=await axios.get(url+"user/orders/"+userid,{
            headers: {
              Authorization: 'Bearer ' + token
            }
        });
        var p=[];
        if(pref.data.length!==0){

            pref.data.forEach((x)=>{
                p.push({
                    value:x,label:x
                })
            })
            setPref(p);
        }
        console.log(ord.data);
        setOrders(ord.data);
    }

    const handleToggle=()=>{
        if(togglePrefs){
            document.getElementById("pref-butt").innerText="Cancel";
            document.getElementById("pref-butt-sub").style.display="block";
            setTogglePrefs(!togglePrefs);
        }
        else{
            window.location.reload(true);
        }
    }

    const handlePrefSubmit=async()=>{
        var prefs=[];
        userpref.forEach((x)=>{
            prefs.push(x.value)
        })
        console.log(prefs);
        axios.put(url+"user/addPrefs/"+userid,{
            "prefs":prefs
        },{
            headers: {
              Authorization: 'Bearer ' + token
            }
        });
        setTimeout(()=>{
            window.location.reload(true)
        },1500)
    }

    useEffect(()=>{
        if(!islogged)
        nav("/")
        else{
            getData();
            setTimeout(()=>{
                setLoaded(true);
            },1500)
        }
    },[])

    const handleBalanceAdd=async()=>{
        axios.put(url+"user/addBalance/"+userid,{},{
            headers: {
              Authorization: 'Bearer ' + token
            }
        }).then(()=>{
            setBalToast(true);
            setTimeout(()=>{
                window.location.reload(true)
            },1500)
        }).catch(error=>console.log("Error",error));
    }

    const [balToast,setBalToast]=useState(false);

    if(!loaded)
    return(
        <Loading/>
    )
    else
    return(
        <div className="profile-outer">
            <Snackbar anchorOrigin={{vertical:'bottom',horizontal:'right'}} open={balToast} onClose={()=>setBalToast(!balToast)} autoHideDuration={3000}>
                <Alert sx={{backgroundColor:'green',width:'300px',color:'white',translate:'15px 0'}} variant="success">
                    Processing...                  
                 </Alert>
            </Snackbar>
            <div className="profile-box">
                <div className="profile-nav">
                    <CustomNavbar currentPage="my-profile"/>
                </div>
                <div className="profile-prefs">
                    <div className="prefs-box">
                    <div className="prefs-head">
                        <h3>Manage Wallet</h3>
                        </div>
                            <h5>Wallet Balance: ${wallet}</h5>
                        <Button style={{marginTop:"1%"}} variant="outline-dark" onClick={handleBalanceAdd}>Add Balance</Button>
                        </div>

                    </div>
                <div className="profile-prefs">
                    <div className="prefs-box">
                        <div className="prefs-head">
                        <h3>Your Preferences</h3>
                        </div>
                        {userpref.length===0 &&
                    <div className="no-books">
                        You haven't set any preferences.
                    </div>
                        }
                        <div>
                        <Select
                        components={animatedComp}
                        closeMenuOnSelect={false}
                        isMulti
                        options={options}
                        value={userpref}
                        isDisabled={togglePrefs}
                        onChange={handleSelectChange}/>
                        </div>
                    <div className="prof-b-container">
                        <Button className="profs-butts" id="pref-butt-sub" variant="outline-dark" onClick={handlePrefSubmit}>Submit</Button>
                        <Button className="profs-butts" id="pref-butt" variant="outline-dark" onClick={handleToggle}>Edit Preferences</Button>
                    </div>
                    </div>
                </div>
                <div className="profile-orders">
                    <div className="orders-box">
                        <h3>Your Orders</h3>
                            {orders.length!==0?(
                             <div>
                             {orders.map((o)=>{
                                return(
                                    <div className="order-cont">
                                        <div className="order-dets">
                                            <h4>Order ID : {o.id}</h4>
                                            <h5>Books:</h5>
                                            {o.books.map((i)=>{
                                                return(
                                                    <p>{i.title}</p>
                                                    )
                                            })}
                                            <h5>Total : ${o.total}</h5>
                                            <h5>Placed On : {o.orderDate}</h5>
                                        </div>
                                    </div>
                                )
                             })}
                             </div>   
                            ):
                            <div className="no-books">
                            You haven't placed any orders.
                            </div>
                            }
                    </div>
                </div>
                <div className="profile-reviews">
                <div className="reviews-box">
                            <h3>Your Reviews</h3>
                            {reviews.length!==0?(
                            <div>
                                {reviews.map((r)=>{
                                    return(
                                    <div className="review-cont">
                                    <div className="review-custName">
                                        <h4>Book : {r.booktitle}</h4>
                                        <h6>Posted On: {r.postedOn}</h6>
                                        <hr/>
                                    </div>
                                    <div className="review-rating">
                                    <StarOutline/>{r.rating}
                                    </div>
                                    <div className="review-comment">
                                        <p>{r.review}</p>
                                    </div>
                                    </div>
                            )
                        })}
                        </div>
                        ):
                        <div className="no-books">
                        You haven't given any reviews.
                        </div>
                        }
                        </div>
                </div>
                <div className="footer">
                <Footer/>
            </div>
            </div>
            <Name/>
        </div>
    )
}