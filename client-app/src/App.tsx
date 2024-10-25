import { BrowserRouter as Router } from 'react-router-dom'
import './App.css'
import { AppRoutes } from './components/Routes'

function App() {
  const routes = AppRoutes({})
  return (
    <div className="App">
      <Router>
        {routes}
      </Router>
    </div>
  );
}

export default App;
