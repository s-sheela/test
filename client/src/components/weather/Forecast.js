import React from 'react';

function Forecast({data}) {
  return(
    <>
    <tr>    
    <td>{data.date}</td>
    <td>{data.desc}<br/>
      <img src={data.icon}></img>
    </td>
    <td>{data.temp} &#8451;</td>
    <td>{data.feellike}</td>
    </tr>
    </>
    
  );
}

export default Forecast;