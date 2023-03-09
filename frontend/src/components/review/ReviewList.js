import '../../css/review.css';
import { FaStar } from 'react-icons/fa';
import { useState } from 'react';
import { useEffect } from 'react';
import styled from 'styled-components';
import { useLocation, useNavigate } from 'react-router-dom';
import { MDBCollapse } from 'mdb-react-ui-kit';
import axios from 'axios';
import { baseUrl } from '../commonApi/mainApi';

const ReviewList = ({ reviews, deleteReview, id }) => {
  const [score, setScore] = useState([false, false, false, false, false]);
  const ARRAY = [0, 1, 2, 3, 4];
  const [foldShow, setFoldShow] = useState(false);
  const toggleShow = () => setFoldShow(!foldShow);
  const [inputs, setInputs] = useState('');
  const navigator = useNavigate();
  const location = useLocation();
  const [star, setStar] = useState(0);

  useEffect(() => {
    starScore(reviews.review_rating);
  }, []);

  const starScore = (index) => {
    let star = [...score];
    for (let i = 0; i < index; i++) {
      star[i] = i <= index ? true : false;
    }
    setScore(star);
  };

  const updateReview = async () => {
    const form = new FormData();
    form.append('review_num', reviews.review_num);
    form.append('user_id', localStorage.getItem('userid'));
    form.append('book_num', id);
    form.append('review_content', inputs);
    form.append('review_rating', star);
    form.append('review_writer', localStorage.getItem('usernickname'));

    const config = {
      headers: { 'Content-Type': 'application/json' },
    };

    await axios
      .put(baseUrl + '/review/update', form, config)
      .then((response) => {
        setInputs('');
        window.location.replace('/book/' + id);
      });
  };

  const handleValueChange = (e) => {
    //리뷰 400자 초과 안되게 자동 자름
    let review = e.target.value;
    let lengthCheckEx = /^.{400,}$/;
    if (lengthCheckEx.test(review)) {
      //400자 초과 컷
      review.value = review.value.substr(0, 400);
    }

    setInputs(e.target.value);
  };

  const handleBack = (e) => {
    e.preventDefault();
    navigator(-1);
  };

  // 별점
  const [clicked, setClicked] = useState([false, false, false, false, false]);

  const handleStarClick = (index) => {
    setStar(index + 1);
    let clickStates = [...clicked];
    for (let i = 0; i < 5; i++) {
      clickStates[i] = i <= index ? true : false;
    }
    setClicked(clickStates);
  };

  useEffect(() => {
    sendReview();
  }, [clicked]); //컨디마 컨디업

  const sendReview = () => {
    let score = clicked.filter(Boolean).length;
  };

  return (
    <div className='review-wrap container-fluid colums-row'>
      <div className='review-body container-fluid d-flex'>
        <div className='review-writer col-2 columns-row'>
          {/* <div>{reviews.review_num}</div> */}
          {/* <div>{reviews.user_id}</div> */}
          <div>{reviews.review_writer}</div>
          <div className='review-rating'></div>
          <div>{reviews.review_reporting_date}</div>
          <div className='star-wrap container d-flex'>
            <Stars>
              {ARRAY.map((el, index) => (
                <FaStar
                  key={index}
                  size='25'
                  className={score[el] && 'yellowStar'}
                ></FaStar>
              ))}
            </Stars>
          </div>
        </div>

        <div className='review-content mt-2 col-9'>
          <div>{reviews.review_content}</div>
        </div>
        {(localStorage.getItem('userid') !== null) &
        (parseInt(localStorage.getItem('userid')) === reviews.user_id) ? (
          <div className='review-edit columns-row col-1'>
            <button
              onClick={toggleShow}
              className='review-edit-btn btn btn-search'
              id='btn-review-right'
            >
              수정
            </button>

            <button
              className='btn btn-search review-delete-btn'
              onClick={() => deleteReview(reviews.review_num)}
            >
              삭제
            </button>
          </div>
        ) : (
          <></>
        )}
      </div>

      <MDBCollapse show={foldShow}>
        <Stars>
          <span className='update-average'>
            기존 별점 : {reviews.review_rating}{' '}
          </span>
          {ARRAY.map((el, idx) => {
            return (
              <FaStar
                key={idx}
                size='25'
                onClick={() => handleStarClick(el)}
                className={clicked[el] && 'yellowStar'}
                id='modify-star'
              />
            );
          })}
        </Stars>
        <div className='container-fluid d-flex p-0'>
          <div className='p-0'>
            <textarea rows='4' cols='100' onChange={handleValueChange} />{' '}
          </div>
          <div className='col-1 p-0'>
            <button
              className='btn btn-search review-modify-btn'
              onClick={updateReview}
              value={inputs}
              id='review-modify-inside'
            >
              수정
            </button>
          </div>
        </div>
      </MDBCollapse>
      <hr />
    </div>
  );
};

export default ReviewList;

const Stars = styled.div`
  display: flex;
  padding-top: 5px;

  & svg {
    color: gray;
  }

  .yellowStar {
    color: #fcc419;
  }
`;

const Wrap = styled.div`
  display: flex;
  flex-direction: column;
  padding-top: 15px;
`;

const RatingText = styled.div`
  color: #787878;
  font-size: 20px;
  font-weight: 400;
`;

const StarS = styled.div`
  display: flex;
  padding-top: 5px;

  & svg {
    color: gray;
    cursor: pointer;
  }

  :hover svg {
    color: #fcc419;
  }

  & svg:hover ~ svg {
    color: gray;
  }

  .yellowStar {
    color: #fcc419;
  }
`;
