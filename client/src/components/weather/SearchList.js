import React from 'react';
import Forecast from './Forecast';
import { useState, useEffect } from 'react';

function SearchList({ finalData,city }) {
  const weatherList = finalData.map(data =>  <Forecast key={data.id} data={data} />); 
  
  const [message,setMessage]=useState("");
  const [isOpen, setOpen] = useState(false);
  const [historyData,setHistoryData]=useState([])
   
  const openWindow = (e) => {
    
    e.preventDefault();
    setOpen(!isOpen);
 
    try{  
      fetch("http://localhost:8080/travel/getAll")
        .then(res=>res.json()
        )
        .then((res)=>{
          setHistoryData(res);
        })
    }catch(err){
      console.log(err)
    }
    
  }; 
  
    
  useEffect( () =>
    {

    }, [] )

  const handleSave=e=>{
    e.preventDefault();
    setMessage("");
    try{  
      fetch("http://localhost:8080/travel/add/"+city,{
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify(finalData)

      }).then(()=>{
        setMessage("Itinerary added!!");
        console.log("New Record added");
      })
    }catch(err){
      console.log(err)
    }
  }
  return (
    <div>
      <hr/>
        <div class="overflow-auto">          
          <h2>Weather Forecast for {city}</h2>
          <table className="tbl">
            <thead>              
              <th>Date</th>
              <th>Weather</th>
              <th>Temperature</th>
              <th>Feels Like</th>
            </thead>
            <tbody>
            {weatherList}
            </tbody>
          </table>
      </div><h3>{message}</h3> 
      <div class="box">
        <div align="left"><button className='btn' onClick={handleSave}>Save Itinerary</button>
        </div>
        <div align="right"><button className='btn' onClick={openWindow}>View History</button>
        </div>        
      </div>
      {isOpen && <div class="popup">
                <h2>Itinerary History</h2>
                <a class="close" href="#" onClick= {openWindow}>&times;</a>                
                <div class="content">
                  
                <table className="tbl">
                    <thead>              
                      <th>Date</th>
                      <th>City</th>
                      <th>Weather</th>
                      <th>Temperature</th>
                      <th>Feels Like</th>
                    </thead>
                    <tbody>                  
                    </tbody>
                  </table>
                  </div>
                </div>
      }
      </div>
  );
} 
export default SearchList;