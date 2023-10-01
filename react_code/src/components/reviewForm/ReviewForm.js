import {Form,Button} from 'react-bootstrap';

const ReviewForm = ({handleSubmit, revText, labelText, defaultValue}) => {
  return (
    <div>
      <Form>
        <Form.Group className='mb-3' controlId='exampleForm.ControlTextareal'>
            <Form.Label>{labelText}</Form.Label>
            <Form.Control ref={revText} as="textarea" rows={3} defaultValue={defaultValue}/>
        </Form.Group>
        <Button varient='outline-info' onClick={handleSubmit}>Submit</Button>
      </Form>
    </div>
  )
}

export default ReviewForm
