import { useState,useEffect } from "react"
import "./carousel.css";
import ArrowRightOutlinedIcon from '@mui/icons-material/ArrowRightOutlined';
import { ArrowLeftOutlined } from "@mui/icons-material";
function Carousel({data}){
    const [currentIndex,setIndex]=useState(0);
    const nextImage=()=>{
        setIndex(currentIndex===data.length-1 ? 0:currentIndex+1)
    }
    const prevImage=()=>{
        setIndex(currentIndex===0 ? data.length-1:currentIndex-1)
    }
    useEffect(()=>{
        const intervalId=setInterval(nextImage,5000);
        return()=>{
            clearInterval(intervalId);
        };
    })
    return(
        <div className="carousel-outer">
            <div className="image-carousel">
                <button id="prev-button" onClick={prevImage}><ArrowLeftOutlined className="carousel-icons"/></button>
                <img id="carousel-img" src={data[currentIndex].image} alt="Carousel"/>
                <button id="next-button" onClick={nextImage}><ArrowRightOutlinedIcon className="carousel-icons"/></button>
            </div>
        </div>
    )
}
export default Carousel;