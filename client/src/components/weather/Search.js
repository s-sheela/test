import React, { useState } from 'react';
import SearchList from './SearchList';
import { useEffect, useRef } from 'react';
import {date1,date2,date3} from '../../data/traveldate';

function Search({ details }) {
    const [isData,setData]=useState(false)
    const [finalData, setFinalData] = useState("")
    const [searchCity, setSearchCity] = useState("Tokyo")
    const [traveldate, setTravelDate] = useState({date1})
    const inputRef = useRef({date1});

    const handleSelect=(e)=>{
        setSearchCity(e.target.value);

        console.log(e.target.value);
        if (e.target.value==='Tokyo'){
            inputRef.current.value=date1;
            setTravelDate(date1);
        }
        if (e.target.value==='Moscow'){
            inputRef.current.value=date2;
            setTravelDate(date2);
        } 
        if(e.target.value==='London'){
            inputRef.current.value=date3;
            setTravelDate(date3);
        }
    }
    const handleSearchChange = e => {
        e.preventDefault();
        setData(false);
        setFinalData("");
        search();      
    }
    async function search (){
        
        let result = await fetch(
            "http://localhost:8080/forecast?city="+searchCity+"&date="+inputRef.current.value,
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                },
            }
        )
        result = await result.json()
        setData(true) ;
        setFinalData( result );
    }
    useEffect( () =>
    {
       // search();

    }, [] )

function searchList() {
    if (finalData.length)
    return (        
            <SearchList finalData={finalData} city={searchCity} />        
    );
}

return (
    <section>
        <div>
            <h2>Enter City</h2>
        </div>
        <div>
            <label className="lbl">Select City</label>
            <select  defaultValue="Select City" onChange={handleSelect}>
                <option value="Tokyo">Tokyo</option>
                <option value="Moscow">Moscow</option>
                <option value="London">London</option>
            </select>
            
            <label className="lbl">Travel Date</label>
            <input ref={inputRef} className="ip" readOnly/>

            <button class="btn" onClick={handleSearchChange}>Submit</button>
        </div>
  
        {isData && 
            <div>
            {searchList()}
            </div>
        }
    </section>
);
}

export default Search;