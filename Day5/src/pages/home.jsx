import CustomNavbar from "../components/navbar";
import "../assets/styles/home.css";
import Name from "../components/name";
import Homesidebar from "../components/homesidebar";
import {  useSelector } from "react-redux/es/hooks/useSelector";
import { useNavigate } from "react-router-dom";
import Carousel from "../components/carousel";
import { useEffect } from "react";
import data from "../assets/json/imagecarousel.json";
import BookCard from "../components/bookcard";
import books from "../assets/json/imagecarousel.json";
import Footer from "../components/footer";


function Home(){
    const nav=useNavigate();
    const userName=useSelector((state)=>state.login.userName);
    const isLogged=useSelector((state)=>state.login.isLoggedin)
    useEffect(()=>{
        if(!isLogged)
            nav("/");
    },[isLogged,nav])
    return(
        <div className="home-outer">
            <div className="home-box">
                <Homesidebar />
                <div className="home-nav">
                <CustomNavbar currentPage="home" name={userName}/>
                </div>
                <div className="home-top">
                    <div className="home-carousel">
                        <Carousel data={data}/>
                    </div>
                    <div className="cont-reading"id="bookmarks">
                        <div className="cont-reading-title">
                            <h2>Continue Reading</h2>
                        </div>
                        <div className="cont-reading-books">
                            <div className="c-books" >
                                {books.map((b)=>{
                                    return(
                                        <BookCard data={b}/>
                                    )
                                })}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="home-tab">
                    <div className="home-bestsellers" id="wishlist">
                    <h2>Your Wishlist</h2>
                        <div className="best-seller-books">
                        {books.map((b)=>{
                        return(
                            <BookCard data={b}/>
                            )
                        })}
                        </div>
                    </div>
                    <div className="home-bestsellers" id="foryou">
                    <h2>For You</h2>
                        <div className="best-seller-books">
                        {books.map((b)=>{
                        return(
                            <BookCard data={b}/>
                            )
                        })}
                        </div>
                    </div>
                    <div className="home-bestsellers" id="bestseller">
                    <h2>Best Sellers</h2>
                        <div className="best-seller-books">
                        {books.map((b)=>{
                        return(
                            <BookCard data={b}/>
                            )
                        })}
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
export default Home;