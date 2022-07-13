import React from 'react';
import Search from './components/weather/Search';
import loadData from './data/loadData';

function App() {
  return (
    <div className="box">
      
      <Search details={loadData}/>
    </div>
  );
}

export default App;