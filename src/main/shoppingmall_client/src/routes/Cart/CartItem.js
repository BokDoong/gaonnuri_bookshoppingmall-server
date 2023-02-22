import axios from "axios";
import { useEffect, useState } from "react";

const CartItem = ({data,getData,checkedItemHandler}) => {
    const [count,setCount] = useState(data.count);

    useEffect(()=>{
        const uri = "http://localhost:3001/member_cart/"+data.id;
        const temp = data;
        temp.count = count;
        axios.put(uri,temp);
        console.log(temp);
    },[count,data])

    const checkHandler = (e) => {
        checkedItemHandler(data.id, e.target.checked);
    };

    const onMinusClick = () => {
        if(count!==1)setCount(count-1);
    }
    const onPlusClick = () => {
        setCount(count+1);
    }
    const onDeleteClick = () => {
        const uri = "http://localhost:3001/member_cart/"+data.id;
        axios.delete(uri);
        getData();
    }
    return (
        <div className="cart_table_bottom">
            <div className="cart_table_cell_check">
                <span style={{minWidth: "24px",minHeight: "24px", lineHeight: "24px"}}><input onChange={checkHandler}className="cart_checkbox" type="checkbox"/></span>
            </div>
            <div className="cart_table_cell_info">
                <div style={{display:"flex"}}>
                    <img className="cart_img" src="https://thumbs.dreamstime.com/b/transparent-designer-must-have-fake-background-39672616.jpg"/>
                    <div style={{marginLeft:"10px"}}>
                        <div className="cart_item_name">{data.item_name}</div>
                        <div className="cart_item_price">{data.price}원</div>
                    </div>
                </div>
            </div>
            <div className="cart_table_cell_count">
                <div style={{width:"108px", margin:"auto"}}>
                    <div style={{display:"flex"}}>
                        <button onClick={onMinusClick} className="countbtn">-</button>
                        <button style={{color:"black"}}className="countbtn">{count}</button>
                        <button onClick={onPlusClick} className="countbtn">+</button>
                    </div>
                </div>
            </div>
            <div className="cart_table_cell_price">{count*data.price}원</div>
            <div className="cart_table_cell_choice"><button className="cart_delete_btn" onClick={onDeleteClick}>삭제하기</button></div>
        </div>
    )
}

export default CartItem;