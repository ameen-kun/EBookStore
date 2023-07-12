import "./bookcard.css";
import { StarOutlineSharp } from "@mui/icons-material";
const BookCard=({data})=>{
    return(
        <div className="book-card-outer">
            <div className="book-card-imdiv">
                <img className="book-card-img"src={data.image}/>
            </div>
            <div className="book-card-details">
                <div className="book-card-det">
                    <h4>{data.title}</h4>
                    <p className="book-card-author">{data.author}</p>
                    <div className="book-card-rating">
                        <StarOutlineSharp id="rating-star"/>
                        {data.rating}
                    </div>
                </div>
            </div>
        </div>
    );
}
export default BookCard;