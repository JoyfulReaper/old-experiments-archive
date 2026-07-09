import React, {useState} from 'react';
import {Col, Row, Form, FormGroup, Input, Label, Container, Button} from 'reactstrap';
import Result from './Result.js';

const Weather = () => {
    const baseUrl = "https://localhost:7200/api/Weather/";

    const [data, setData] = useState('');
    const [location, setLocation] = useState('');

    const getWeather = () => {
        if(location === '')
        {
            alert("Location must be set!");
            return;
        }

        const params = new URLSearchParams({
            location: location
        });
        fetch(baseUrl + "current?" + params)
            .then(response => response.json())
            .then(data => {
                console.log("out", data);
                setData(data);
                document.getElementById("result").style.display="block";
            })
            .catch(error => alert("Error: " + error.message));
    }

    return (
        <div>
            <Container>
                <Row className="mt-5">
                    <Col xs="6">
                        <Form
                            onSubmit ={ 
                                e => {
                                    e.preventDefault();
                                    getWeather();
                                }
                            }
                        >
                            <FormGroup>
                                <Label for="location">
                                    Location:
                                </Label>
                                <Input
                                    id="location"
                                    name="location"
                                    type="text"
                                    onChange={e => setLocation(e.target.value)}
                                />
                            </FormGroup>
                            <Button 
                                type="submit"
                                color="primary"
                                id="btnGo"
                                onClick={getWeather}
                            >
                                Go!
                            </Button>
                        </Form>
                    </Col>
                    <Col>
                        {data !== '' && <Result data={data}/>}
                    </Col>
                </Row>
            </Container>
        </div>
    )
}

export default Weather;