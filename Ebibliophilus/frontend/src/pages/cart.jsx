import CustomNavbar from "../components/navbar"
import "../assets/styles/cart.css";
import { Row } from "react-bootstrap";
import Col from "react-bootstrap/Col";
import CartBookCard from "../components/cartbookcard";
import { useSelector } from "react-redux";
import Button from "react-bootstrap/Button";
import Footer from "../components/footer";
import Name from "../components/name";
import { Snackbar } from "@mui/material";
import Alert from "@mui/material/Alert";
import { useState } from "react";
import { useEffect } from "react";
import axios from "axios";
import Loading from "../components/loading";
import { useNavigate } from "react-router-dom";
import { url } from "../util";

function Cart(){
    const userid=useSelector((state)=>state.login.userDetails.id);
    const token=useSelector((state)=>state.login.token);
    const [books,setBooks]=useState([]);
    const [total,setTotal]=useState(0);
    const [loaded,setLoaded]=useState(false);
    const islogged=useSelector((state)=>state.login.isLoggedin);
    const nav=useNavigate();
    const [wallet,setWallet]=useState(0);
    const getData=async()=>{
        try{
            const cartbooks=await axios.get(url+"user/cart/"+userid,{
                headers: {
                  Authorization: 'Bearer ' + token
                }
            });
            setBooks(cartbooks.data.books);
            setTotal(cartbooks.data.total);
            const walletval=await axios.get(url+"user/wallet/"+userid,{
                headers: {
                  Authorization: 'Bearer ' + token
                }
            });
            setWallet(walletval.data);
        }
        catch(error){
            console.log(error);
        }

    }

    useEffect(()=>{
        if(!islogged){
            nav("/")
        }
        else{
            getData();
            setTimeout(()=>{
                setLoaded(true)
            },1500)
        }
    },[])


    const handleRemove=async (book)=>{
        await axios.delete(url+"user/deleteFromCart/"+userid+"/"+book.id,{
            headers: {
              Authorization: 'Bearer ' + token
            }
        })
        .catch((error)=>{
            console.log(error);
        })
        setCartToast(true);
        setTimeout(()=>{
            window.location.reload(true);
        },1000)
    }

    const [cartToast,setCartToast]=useState(false);
    const handleToastClose=()=>{
        setCartToast(false);
    }
    const [orderToast,SetOrderToast]=useState(false);
    const handleOrderToast=()=>{
        SetOrderToast(false);
    }
    const placeOrder=async ()=>{
        if(total!=0){
            if(wallet>=total){
                await axios.post(url+"user/moveBooks/"+userid,{},{
                headers:{
                    Authorization:"Bearer "+token
                }
            }).catch(error=>console.log(error));
            SetOrderToast(true);
            setTimeout(()=>{
                window.location.reload(true);
            },1500)
            }
            else{
                setFundToast(true);
            }
        }
    }

    const [fundToast,setFundToast]=useState(false);

    if(!loaded)
    return(
        <Loading/>
    )
    else
    return(
        <div className="cart-outer">
            <Snackbar anchorOrigin={{vertical:'bottom',horizontal:'right'}} open={cartToast} onClose={handleToastClose} autoHideDuration={3000}>
                <Alert sx={{backgroundColor:'red',width:'300px',color:'white',translate:'15px 0'}} variant="success">
                    Book Deleted from Cart!                   
                 </Alert>
            </Snackbar>
            <Snackbar anchorOrigin={{vertical:'bottom',horizontal:'right'}} open={fundToast} onClose={()=>setFundToast(!fundToast)} autoHideDuration={3000}>
                <Alert sx={{backgroundColor:'red',width:'300px',color:'white',translate:'15px 0'}} variant="success">
                 Insufficient Funds!!!
                 </Alert>
            </Snackbar>
            <Snackbar anchorOrigin={{vertical:'bottom',horizontal:'right'}} open={orderToast} onClose={handleOrderToast} autoHideDuration={3000}>
                <Alert sx={{backgroundColor:'green',width:'300px',color:'white',translate:'15px 0'}} variant="success">
                    Processing...                  
                 </Alert>
            </Snackbar>
            <div className="cart-box">
                <div className="cart-nav">
                    <CustomNavbar currentPage="my-cart"/>
                </div>
                <div className="cart-cont">
                    <div className="cart-title">
                    <h3>Your Cart</h3>
                    </div>
                    <div className="cart-items">
                        <div className="cart-books">
                            {books.length!==0?(

                                <Row>
                        {books.map((n)=>{
                            return(
                                <Col md="4" className="exp-book-col">
                                <CartBookCard handleClick={handleRemove} data={n}/>
                                </Col>
                                )
                            })}
                        </Row>
                        ):
                        <div className="no-books">
                            You haven't added any books to your cart.
                        </div>
                        }
                        </div>
                        <div className="cart-details">
                            <div className="cart-det">
                                <h3>Cart Details</h3>
                                <div className="cart-pars">
                                <h5>No. of Books : {books.length}</h5>
                                </div>
                                <div className="cart-pars">
                                <h5>Total Price : </h5>
                                <h2>
                                 ${total}
                                </h2>
                                <h5>Wallet : ${wallet}</h5>
                                <div className="check-out-button-cont">
                                    <Button className="check-out-button"variant="warning" onClick={placeOrder}>Confirm and Check-Out</Button>
                                </div>
                                </div>
                            </div>
                        </div>
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
export default Cart;